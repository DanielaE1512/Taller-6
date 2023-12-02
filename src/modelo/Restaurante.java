package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante {
	
	//Atributos
	
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private ArrayList<Producto> menuBase;
	private ArrayList<Ingrediente> ingredientes;
	
	//Constructor
	
	public Restaurante()
	{
		combos = new ArrayList<Combo>();
		pedidos = new ArrayList<Pedido>();
		menuBase = new ArrayList<Producto>();
		ingredientes = new ArrayList<Ingrediente>();
	}
	
	//Otro métodos
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		Pedido nuevoPedido=new Pedido(nombreCliente,direccionCliente);
		this.pedidoEnCurso = nuevoPedido;
		pedidos.add(nuevoPedido);
	}
	
	public void cerrarYGuardarPedido()
	{
		File archivo = new File("data/facturas/" + String.valueOf(pedidoEnCurso.getIdPedido()));
		pedidoEnCurso.guardarFactura(archivo);
		this.pedidoEnCurso = null;
		
	}
	
	public Pedido getPedidoEnCurso()
	{
		return this.pedidoEnCurso;
		
	}
	
	public ArrayList<Pedido> getPedidos()
	{
		return this.pedidos;
	}
	
	public ArrayList<Producto> getMenuBase()
	{
		return this.menuBase;
		
	}
	public ArrayList<Ingrediente> getIngredientes()
	{
		return this.ingredientes;
		
	}
	
	public ArrayList<Combo> getCombos()
	{
		return this.combos;
		
	}
	
	
	public void cargarInformacionRestaurante(File archivoIngredientes,File archivoMenu, File archivoCombos )throws FileNotFoundException, IOException, ProductoRepetidoException, IngredienteRepetidoException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException, IOException, IngredienteRepetidoException
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea= br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			
			String nombre = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			
			Ingrediente nuevoIngrediente =new Ingrediente(nombre,costoAdicional);
			
			if (ingredientes.contains(nuevoIngrediente))
			{
				throw new IngredienteRepetidoException(nuevoIngrediente.getNombre());
			}
			else {
			
			ingredientes.add(nuevoIngrediente);
			}
			linea= br.readLine();
		}
		
		br.close();
	}
	
	private void cargarMenu(File archivoMenu) throws FileNotFoundException, IOException, ProductoRepetidoException
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea= br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			
			Producto nuevoProducto =new ProductoMenu(nombre,precio);
			
			if (menuBase.contains(nuevoProducto))
			{
				throw new ProductoRepetidoException(nuevoProducto.getNombre());
			}
			else {
			menuBase.add(nuevoProducto);}
			
			linea= br.readLine();
		}
		
		br.close();
	}
	
	private void cargarCombos(File archivoCombos) throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea= br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			
			String nombreCombo = partes[0];
			double descuento = Double.parseDouble(partes[1].replace("%", ""))/100;
			String producto1 = partes[2];
			String producto2 = partes[3];
			String producto3 = partes[4];
			
			Combo nuevoCombo = new Combo(descuento,nombreCombo);
			
			combos.add(nuevoCombo);
			
			for (Producto producto: menuBase)
			{
				if (producto1.equals(producto.getNombre()))
				{
					nuevoCombo.agregarItemACombo(producto);
				}
				if (producto2.equals(producto.getNombre()))
				{
					nuevoCombo.agregarItemACombo(producto);
				}
				if (producto3.equals(producto.getNombre()))
				{
					nuevoCombo.agregarItemACombo(producto);
				}
			}
			linea= br.readLine();
		}
		
		br.close();
	}

	
	
	
}

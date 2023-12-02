package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {

	// Atributos
	
	private static int numeroPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	// Constructor
	
	public Pedido (String nombreCliente, String direccionCliente) {
		
		this.nombreCliente= nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = ++numeroPedidos;
		itemsPedido= new ArrayList<Producto>();
	}
	
	// Otros métodos
	
	public int getIdPedido() {
		
		return this.idPedido;
	}
	
	public void agregarProducto(Producto itemPedido) throws Exception {
		
		itemsPedido.add(itemPedido);
		if (getPrecioTotalPedido() > 150000)
		{
			itemsPedido.remove(itemPedido);
			throw new Exception("Su pedido a superado los 150.000, no es posible agregar el producto");
		}
		
	}
	
	public ArrayList<Producto> getItemPedido(){
		return itemsPedido;
	}
	
	private int getPrecioNetoPedido()
	{
		int precioNeto= 0;
		for (Producto item: itemsPedido)
		{
			precioNeto += item.getPrecio();
		}
		return precioNeto;
	}
	
	private int getPrecioTotalPedido()
	{
		double precioNeto = (double)getPrecioNetoPedido();
		double iva = precioNeto*0.19;
		int precioiva = (int) iva;
		return precioiva + getPrecioNetoPedido();
	}
	
	private int getPrecioIVAPedido()
	{
		double precioNeto = (double)getPrecioNetoPedido();
		double iva = precioNeto*0.19;
		int precioiva = (int) iva;
		return precioiva;
	}
	
	private String generarTextoFactura()
	{
		String factura="\n"+ nombreCliente + "\n"+ direccionCliente;
		
		for (Producto item: itemsPedido)
		{
			factura += item.generarTextoFactura();
		}
		
		factura += "\n\n Precio Neto: $ " + getPrecioNetoPedido();
		factura += "\n IVA: $ " + getPrecioIVAPedido();
		factura += "\n Precio Total: $ " + getPrecioTotalPedido();
		return factura;
	}
	
	public void guardarFactura(File archivo)
	{
		
		try {
            FileWriter fileWriter = new FileWriter(archivo);
            
            fileWriter.write(generarTextoFactura());
            fileWriter.close();
            
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

	}
	
	
}

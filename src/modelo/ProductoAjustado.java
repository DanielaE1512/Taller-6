package modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {

	//Atributos
	
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	// Constructor
	
	public ProductoAjustado (ProductoMenu base)
	{
		this.base= base;
		agregados= new ArrayList<Ingrediente>();
		eliminados= new ArrayList<Ingrediente>();
	}
	
	// Otros métodos
	
	public void agregarIngrediente(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}
	
	public ArrayList<Ingrediente> getIngredienteAgregado()
	{
		return agregados;
	}
	
	public void eliminarIngrediente(Ingrediente ingrediente)
	{
		eliminados.add(ingrediente);
	}
	
	public ArrayList<Ingrediente> getIngredienteEliminado()
	{
		return eliminados;
	}
	
	public int getPrecio() {
		
		int precio = base.getPrecio();
		
		for (Ingrediente ingrediente: agregados)
		{
			precio += ingrediente.getCostoAdicional();
		}
		
		
	
		return precio;
	}

	@Override
	public String getNombre() {
		
		String nombre = base.getNombre();
		
		for (Ingrediente ingrediente: agregados)
		{
			nombre += "\n +" +ingrediente.getNombre();
		}
		
		for (Ingrediente ingrediente: eliminados)
		{
			nombre += "\n -" +ingrediente.getNombre();
		}
	
		return nombre;
	}

	
	public String generarTextoFactura() {
		
		String factura = "\n"+base.getNombre() + " $ " + String.valueOf(base.getPrecio());
		int costo = 0;
		
		for (Ingrediente ingrediente: agregados) 
		{
		
				factura += "\n +" + ingrediente.getNombre()+ " $ " + String.valueOf(ingrediente.getCostoAdicional());
				costo +=ingrediente.getCostoAdicional();
		}
		
		for (Ingrediente ingrediente: eliminados) 
		{
		
				factura += "\n -" + ingrediente.getNombre();
		
		}
		
		factura += "\n Costo total de " +  base.getNombre()+ " ajustado " + "$ " + String.valueOf(getPrecio());
		return factura;
	}

}

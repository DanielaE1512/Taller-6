package modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	//** Atributos
	
			private double descuento;
			
			private String nombreCombo;
			
			private ArrayList<Producto> itemsCombo;
			
	//** Constructores
			
			public Combo(double descuento, String nombreCombo) 
			{
				this.descuento = descuento;
				this.nombreCombo= nombreCombo;
				itemsCombo= new ArrayList<Producto>();
			}
			
	// Otros Métodos
			
			public void agregarItemACombo(Producto itemCombo)
			{
				itemsCombo.add(itemCombo);
			}
			
			public  ArrayList<Producto> getItemsCombo()
			{
				return itemsCombo;
			}
			
			
			public int getPrecio()
			{
				int precio = 0;
				
				for (Producto producto: itemsCombo) 
				{
					precio += producto.getPrecio()- producto.getPrecio()* descuento;
					
				}
				return precio;	
			}
			
			public String generarTextoFactura()
			{
				String factura = "\n"+ this.nombreCombo + ":" ;
				
				for (Producto producto: itemsCombo) 
				{
					if (factura.equals(this.nombreCombo + ":")) {
						
						factura += "\n" + producto.getNombre()+ " $ " + String.valueOf(producto.getPrecio()-producto.getPrecio()* descuento);
					} else {
						factura += "\n" + producto.getNombre()+ " $ " + String.valueOf(producto.getPrecio()-producto.getPrecio()* descuento);
					}
					
					
				}
				return factura;
			}
			
			public String getNombre()
			{
				String nombre= this.nombreCombo + ":" ;
				for (Producto producto: itemsCombo) 
				{
					nombre += "\n "+producto.getNombre();
						
				}
				return nombre;
			}

}

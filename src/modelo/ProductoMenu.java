package modelo;

public class ProductoMenu implements Producto{
	//** Atributos
	
			private String nombre;
			
			private int precioBase;
			
		//** Constructor
			
			public ProductoMenu(String nombre, int precioBase )
			
			{
				this.nombre=nombre;
				this.precioBase= precioBase;
			}
			
		// Métodos para consultar los atributos
			
			public String getNombre()
			{
				return nombre;
			}
			
			public int getPrecio()
			{
				return precioBase;
			}
			
		// Otros métodos
			
			public String generarTextoFactura() 
			{
				return "\n" + nombre + " $ " + String.valueOf(precioBase);
			}

			

		
			
}

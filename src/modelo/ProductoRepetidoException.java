package modelo;

public class ProductoRepetidoException extends HamburguesaException{

	public ProductoRepetidoException(String producto) {
		super("Error: Producto repetido - " + producto);
		
	}

}

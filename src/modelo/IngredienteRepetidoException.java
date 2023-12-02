package modelo;

public class IngredienteRepetidoException extends HamburguesaException{

	public IngredienteRepetidoException(String ingrediente) {
		super("Error: Ingrediente repetido - " + ingrediente);
		
	}

}

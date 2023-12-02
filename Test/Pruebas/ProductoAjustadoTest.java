package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

class ProductoAjustadoTest {

	private ProductoAjustado productoAjustado;
	private Ingrediente queso;
	private Ingrediente cebolla;
	
	
	@BeforeEach
	void setUp() {
		
		ProductoMenu producto = new ProductoMenu("corral", 14000);
		productoAjustado = new ProductoAjustado(producto);
		
		queso = new Ingrediente("queso americano", 2500);
		cebolla = new Ingrediente("cebolla", 1000);
		
		productoAjustado.agregarIngrediente(queso);
		productoAjustado.eliminarIngrediente(cebolla);
	}
	
	@Test
	void testGetPrecio() {
		
		int precioEsperado = 16500;
		int precioResultado = productoAjustado.getPrecio();
		
		assertEquals(precioEsperado, precioResultado, "No se esta calculado el precio de forma correcta");
	}
	
	@Test
	void testAgregarIngrediente() {

		assertTrue(productoAjustado.getIngredienteAgregado().contains(queso), "El ingrediente no fue añadido correctamente");
	}
	
	@Test
	void testEliminarIngrediente() {

		assertTrue(productoAjustado.getIngredienteEliminado().contains(cebolla), "El ingrediente no fue añadido correctamente");
	}
	

	@Test
	void testGenerarTextoFactura() {
	
		
		String facturaEsperada = "\ncorral $ 14000";
		facturaEsperada += "\n +queso americano $ 2500";
		facturaEsperada += "\n -cebolla";
		facturaEsperada += "\n Costo total de corral ajustado $ 16500";
		String facturaResultado = productoAjustado.generarTextoFactura();
		
		assertEquals(facturaEsperada, facturaResultado, "La factura se generó de manera incorrecta");
		
		
	}

}

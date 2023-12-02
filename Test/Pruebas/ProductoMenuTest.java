package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.ProductoMenu;

class ProductoMenuTest {

	@Test
	void testGenerarTextoFactura() {
		ProductoMenu producto = new ProductoMenu("corral", 14000);
		
		String factura = "\ncorral $ 14000";
		String facturaResultado = producto.generarTextoFactura();
		
		assertEquals(factura, facturaResultado, "La factura se generó de manera incorrecta");
		
		
	}

	

}

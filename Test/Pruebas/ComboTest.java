package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Combo;
import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

class ComboTest {

	
	private Combo combo;
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ProductoMenu producto3;
	
	
	@BeforeEach
	void setUp() {
		
		producto1 = new ProductoMenu("corral", 14000);
		producto2 = new ProductoMenu("papas medianas", 5500);
		producto3 = new ProductoMenu("gaseosa", 5000);
		combo = new Combo(0.1,"corral");
		
		combo.agregarItemACombo(producto1);
		combo.agregarItemACombo(producto2);
		combo.agregarItemACombo(producto3);
	
	}
	
	@Test
	void testGetItemsCombo() {
		
		assertTrue(combo.getItemsCombo().contains(producto1), "El producto no se añadio correctamente al combo");
		assertTrue(combo.getItemsCombo().contains(producto2), "El producto no se añadio correctamente al combo");
		assertTrue(combo.getItemsCombo().contains(producto3), "El producto no se añadio correctamente al combo");
	}
	
	@Test
	void testGenerarTextoFactura() {
	
		
		String facturaEsperada = "\ncorral:";
		facturaEsperada += "\ncorral $ 12600.0";
		facturaEsperada += "\npapas medianas $ 4950.0";
		facturaEsperada += "\ngaseosa $ 4500.0";
		String facturaResultado = combo.generarTextoFactura();
		
		assertEquals(facturaEsperada, facturaResultado, "La factura se generó de manera incorrecta");
		
		
	}

}

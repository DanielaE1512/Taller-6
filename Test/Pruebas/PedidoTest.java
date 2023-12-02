package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Combo;
import modelo.Ingrediente;
import modelo.Pedido;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

class PedidoTest {

	private Combo combo;
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ProductoMenu producto3;
	private ProductoAjustado productoAjustado;
	private Ingrediente queso;
	private Ingrediente cebolla;
	private Pedido pedido;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		producto1 = new ProductoMenu("corral", 14000);
		producto2 = new ProductoMenu("papas medianas", 5500);
		producto3 = new ProductoMenu("gaseosa", 5000);
		combo = new Combo(0.1,"corral");
		
		combo.agregarItemACombo(producto1);
		combo.agregarItemACombo(producto2);
		combo.agregarItemACombo(producto3);
		
		productoAjustado = new ProductoAjustado(producto1);
		
		queso = new Ingrediente("queso americano", 2500);
		cebolla = new Ingrediente("cebolla", 1000);
		
		productoAjustado.agregarIngrediente(queso);
		productoAjustado.eliminarIngrediente(cebolla);
		
		pedido = new  Pedido("Daniela", "Carrera 69b #24a -51");
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(combo);
		pedido.agregarProducto(productoAjustado);
	}
	
	@Test
	void testAgregarProducto() 
	{
		assertTrue(pedido.getItemPedido().contains(productoAjustado), "El producto no se añadio correctamente al combo");
		assertTrue(pedido.getItemPedido().contains(producto1), "El producto no se añadio correctamente al combo");
		assertTrue(pedido.getItemPedido().contains(combo), "El producto no se añadio correctamente al combo");
	}
	
	@Test
	void testGuardarFactura() throws IOException {
		File archivo = new File("data/facturas/" + String.valueOf(pedido.getIdPedido()));
		pedido.guardarFactura(archivo);
		
		String facturaEsperada="\nDaniela\nCarrera 69b #24a -51";
		facturaEsperada += "\ncorral $ 14000";
		facturaEsperada += "\ncorral:";
		facturaEsperada += "\ncorral $ 12600.0";
		facturaEsperada += "\npapas medianas $ 4950.0";
		facturaEsperada += "\ngaseosa $ 4500.0";
		facturaEsperada += "\ncorral $ 14000";
		facturaEsperada += "\n +queso americano $ 2500";
		facturaEsperada += "\n -cebolla";
		facturaEsperada += "\n Costo total de corral ajustado $ 16500";
		facturaEsperada += "\n\n Precio Neto: $ 52550";
		facturaEsperada += "\n IVA: $ 9984";
		facturaEsperada += "\n Precio Total: $ 62534";
		
		assertTrue(archivo.exists());
		assertEquals(facturaEsperada, Files.readString(archivo.toPath()));
	}
	
	@Test
	void testAgregarProductoExcepcion() throws Exception 
	{
		Pedido pedidoEjemplo = new  Pedido("Daniela", "Carrera 69b #24a -51");
		ProductoMenu p1 =  new ProductoMenu("corral", 14000);
		ProductoMenu p2 =  new ProductoMenu("corral queso", 16000);
		ProductoMenu p3 =  new ProductoMenu("corral pollo", 15000);
		ProductoMenu p4 =  new ProductoMenu("corralita", 13000);
		ProductoMenu p5 =  new ProductoMenu("todoterreno", 25000);
		ProductoMenu p6 =  new ProductoMenu("todoterreno", 25000);
		ProductoMenu p7 =  new ProductoMenu("todoterreno", 25000);
		
		
		pedidoEjemplo.agregarProducto(p1);
		pedidoEjemplo.agregarProducto(p2);
		pedidoEjemplo.agregarProducto(p3);
		pedidoEjemplo.agregarProducto(p4);
		pedidoEjemplo.agregarProducto(p5);
		pedidoEjemplo.agregarProducto(p6);
		
		assertThrows(Exception.class, () -> pedidoEjemplo.agregarProducto(p7));
	}

}

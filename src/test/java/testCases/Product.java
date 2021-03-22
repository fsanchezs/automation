package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.project.pom.DriverPage;

import Business.SearchProduct;

public class Product {
	SearchProduct searchProduct = null;
	
	@BeforeTest
	void abrirNavegador() throws InterruptedException, IOException, EmailException {
		searchProduct = new SearchProduct(DriverPage.obtenerNagevador("chrome"));
		searchProduct.accesoWeb();
	}
	
	@Test
	public void searchProduct() throws InterruptedException, IOException {
		assertEquals(true, searchProduct.chooseProduct().booleanValue());
	}
	
	@AfterTest
	public void cerrarNavegador() {
		searchProduct.cerrar();
	}
	
}

package com.project.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public final class DriverPage {

	private static WebDriver driver = null;
	private static String navegadorPagina = null;

	public static WebDriver obtenerNagevador(String navegador) { // para establecer el navegador
		if (driver == null || (navegadorPagina != null && !navegadorPagina.equalsIgnoreCase(navegador))) {
			switch (navegador) {

			case "chrome":
				System.setProperty("webdriver.chrome.driver", 
						"C:\\Users\\N A N D O\\Workspace\\T_ebay\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				break;

			case "explorer":
				System.setProperty("webdriver.ie.driver",
						"C:\\Users\\N A N D O\\Workspace\\T_ebay\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;

			}
		}
		return driver;
	}

	private DriverPage() {
	}

}

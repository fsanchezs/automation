package com.project.pom;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	
	public WebDriver driver;
	public WebElement elementOpcion;
	
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> findElements(WebElement element, By locator){
		return element.findElements(locator);
	}
	
	public List<WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	
	public WebElement findElementsOpcion(String marca, By locator, int verifica){
		String cadena = "";
		List<WebElement> seleccion1 = driver.findElements(locator);
		List<WebElement> seleccion = driver.findElements(locator);
		espera();
		for (int i=0; i<seleccion.size();i++){
			if (verifica == 1){
				cadena = seleccion.get(i).getText();
				cadena = cadena.substring(0,cadena.indexOf("(",0)-1);
			}else {
				cadena = seleccion.get(i).getText();
			}
			
			if (cadena.equals(marca)) {
				elementOpcion = seleccion.get(i);
				break;
			}
		}
		return elementOpcion;
	}
	
	public void espera () {
		driver.manage().timeouts().implicitlyWait(1500,TimeUnit.MILLISECONDS) ;
	}
	
	public String getText(WebElement element){
		return element.getText();
	}

	public String getText(By locator){
		return driver.findElement(locator).getText();
	}
	
	public void type(String input, By locator) {
		driver.findElement(locator).sendKeys(input);
	}
	
	public void type(String input, By locator, String e) {
		driver.findElement(locator).sendKeys(input);
		switch (e) {
		case "Enter":
			driver.findElement(locator).sendKeys(Keys.ENTER);
			break;
		default:
			driver.findElement(locator).sendKeys(Keys.TAB);
			break;
		}		
	}
	
	public void printText(By locator, int tamaño) {
		List<WebElement> lista = driver.findElements(locator);
		for (int i=0; i<=tamaño; i++) {
			System.out.println("Producto " + i + 1 + ": " + ", Precio: "+ lista.get(i).getText()); 
		}
	}
	
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public Boolean isDisplay(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void get(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void listDropbox(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);		
	}

	public WebElement EsperarObjeto(By locator) {
		return (new WebDriverWait(driver, 120)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void capturaPantalla(String nombreArchivo) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		FileHandler.copy(scrFile, new File("C:\\Users\\N A N D O\\Workspace\\T_ebay\\Evidencias\\" + nombreArchivo + ".png"));
	}  
	
	public Base(WebDriver driver) {
		this.driver = driver;
	}
	
	public void sendEmail() throws EmailException{
		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("C:\\Users\\N A N D O\\Workspace\\T_ebay\\Reports\\learn_automation2.html");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Report");
		  attachment.setName("reporte");

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  email.setSSL(true); 
		  email.setSmtpPort(587);
		  email.setAuthentication("f.sanchez630@gmail.com", "xxxxxxx");
		  email.addTo("f.sanchez630@gmail.com", "Fernando");
		  email.setFrom("cdorival@auna.pe", "Carlos");
		  email.setSubject("Test Case - QA Automation");
		  email.setMsg("Mensaje enviado de las pruebas realizadas a la pÃ¡gina de ebay");

		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();
	}
	
}

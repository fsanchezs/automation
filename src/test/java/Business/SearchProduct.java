package Business;

import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.project.pom.Base;

public class SearchProduct extends Base {
	public WebDriver driver;
	WebElement elementMarca;
	Base base = new Base(driver);
	
	By txtProducto = By.xpath("//input[@id='gh-ac']");
	By btnBuscar = By.xpath("//input[@id='gh-btn']");
	By opcionTodo = By.xpath("//div[@id='s0-14-11-0-1-2-6-0-2[1]-20[0]-4']");
	By seccionMarca = By.xpath("//form[@id='x-overlay__form']/child::div[@class='x-overlay__container']/child::div[@class='x-overlay__wrapper--left']/div/div/span");
	By txtMarcaAdidas = By.xpath("//form[@id='x-overlay__form']/child::div[@class='x-overlay__container']/child::div[@class='x-overlay__wrapper--right']/div/div/fieldset/div/div/following-sibling::div[1]/div/label/div/div/span[@class='cbx x-refine__multi-select-cbx']");
	By btnAplicar = By.xpath("//body/div[@id='refineOverlay']/div[2]/div[1]/form[1]/div[3]/div[2]/button[1]");	
	By talla = By.xpath("//div[@id='srp-river-main']/div[2]/ul/li/div/div[2]/div[4]/div[1]/span");
	By lblResultado = By.xpath("//body/div[4]/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/h1[1]/span[1]");
	By btnOrdenamiento = By.xpath("//body/div[4]/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/h1[1]/span[1]");
	By opcionMenorPrecio = By.xpath("//body/div[4]/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/h1[1]/span[1]");
	By lblPrecio = By.xpath("//div[@id='srp-river-main']/div[2]/ul/li/div/div[2]/div[4]/div[1]/span");
	By btnOrdenamientoMayor = By.xpath("//body[1]/div[4]/div[6]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/span[1]/span[1]/div[1]/a[5]");
	
	Boolean flag = false;
	
	public void accesoWeb() {
		String url = "https://www.ebay.com/";
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public Boolean chooseProduct() throws InterruptedException, IOException, EmailException {

		type("Gorra", txtProducto);
		click(btnBuscar);
		espera();
		click(opcionTodo); 
		espera();
		click(findElementsOpcion("Marca",seccionMarca,0));
		espera();
		click(findElementsOpcion("adidas",txtMarcaAdidas,1));
		espera();
		click(btnAplicar);
		espera();
		click(talla);
		System.out.println("El resultado de productos es: "+getText(lblResultado));
		click(btnOrdenamiento);
		click(opcionMenorPrecio);
		printText(lblPrecio,5);
		espera();
		click(btnOrdenamiento);
		espera();
		click(btnOrdenamientoMayor);
		espera();
		
		sendEmail();
		return true;
	}
	
	public SearchProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void cerrar() {
		driver.quit();
	}
	
}

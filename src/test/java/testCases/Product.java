package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.apache.commons.mail.EmailException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.project.pom.DriverPage;
import com.project.pom.Utility;

import Business.SearchProduct;

public class Product {
	 ExtentReports extent;
	 ExtentTest logger;
	SearchProduct searchProduct = null;
	
	@BeforeTest
	void abrirNavegador() throws InterruptedException, IOException, EmailException {
		searchProduct = new SearchProduct(DriverPage.obtenerNagevador("chrome"));
		searchProduct.accesoWeb();
	}
	
	@BeforeMethod
	public void setup()
	{
	    ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/learn_automation2.html");
	    extent = new ExtentReports();
	    extent.attachReporter(reporter);
	    logger=extent.createTest("LoginTest");
	}
	
	@Test
	public void searchProduct() throws InterruptedException, IOException, EmailException {
		assertEquals(true, searchProduct.chooseProduct().booleanValue());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp=Utility.getScreenshot(searchProduct.driver);
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		extent.flush();
	}
	
	@AfterTest
	public void cerrarNavegador() {
		searchProduct.cerrar();
	}
	
}

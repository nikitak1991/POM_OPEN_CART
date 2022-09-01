package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;


public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;
	
	public Driverfactory factory;
	
	public LoginPage loginpage;
	public AccountPage accountpage;
	public SearchResultPage searchResPage;
	public ProductDetailPage productdetailpage;
	public RegistrationPage regispage;
	
	public  SoftAssert softassert ;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		factory = new Driverfactory();
		prop = factory.initPropperty();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = factory.initDriver(prop); // call by reference
		loginpage = new LoginPage(driver);
       softassert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

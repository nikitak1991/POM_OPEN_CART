package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;


public class BaseTest {
	public Driverfactory factory;
	public WebDriver driver;
	public LoginPage loginpage;
	public Properties prop;
	public AccountPage accountpage;
	public SearchResultPage searchResPage;
	public ProductDetailPage productdetailpage;
	public  SoftAssert softassert ;
	public RegistrationPage regispage;
	
	
	@BeforeTest
	public void setUp() {
		factory = new Driverfactory();
		prop = factory.initPropperty();
		driver = factory.initDriver(prop); // call by reference
		loginpage = new LoginPage(driver);
       softassert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

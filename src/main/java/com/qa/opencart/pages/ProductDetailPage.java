package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductDetailPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	private Map<String, String> hmap;
	// By locators

	By addCartIcon = By.cssSelector(" div#cart.btn-group button i");
	By addCartButton = By.cssSelector("div.form-group #button-cart");
	By productmetadata = By.xpath("(//div[@class='col-sm-4']//ul)[1]");
	By productHeader = By.cssSelector("div.col-sm-4 h1");

	// Constructor

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public boolean isAddCartIconExits() {
		return eleutil.waitForElementVisible(addCartIcon, AppConstants.SMALL_DEFAULT_TIMEOUT).isDisplayed();
	}

	public boolean isAddCartButtonExits() {
		return eleutil.waitForElementVisible(addCartButton, AppConstants.SMALL_DEFAULT_TIMEOUT).isDisplayed();
	}

	public Map<String, String> getProductInfo() {
		hmap = new HashMap<String, String>();
		hmap.put("Productname", productHeaderValue());
		productMetaDeta();
		return hmap;
	}

	public String productHeaderValue() {
		String productheadervalue = eleutil.doElementGetText(productHeader);
		System.out.println(productheadervalue);
		return productheadervalue;
	}

	public void productMetaDeta() {
		List<String> productmetadatlist = eleutil.getAllElementsTextList(productmetadata,
				AppConstants.SMALL_DEFAULT_TIMEOUT);
		for (String e : productmetadatlist) {
			String array[] = e.split(":");
			String key = array[0].trim();
			String value = array[1].trim();
			hmap.put(key, value);
		}
	}

}

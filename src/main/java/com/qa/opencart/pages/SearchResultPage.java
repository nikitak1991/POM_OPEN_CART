package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	// By locators

	By productCount = By.cssSelector("div.product-thumb");
	

	// Constructor

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	//Actions
	public int getSearchProductCount() {
		return eleutil.waitForElementsToBeVisible(productCount, AppConstants.SMALL_DEFAULT_TIMEOUT).size();
	}
	
	public ProductDetailPage selectProduct(String proctname) {
		By product = By.linkText(proctname);
		eleutil.doClick(product);
		return new ProductDetailPage(driver);
	}
}

package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	// By locators

	By footerheaderlist = By.cssSelector("div h5");
	By footerlsit = By.xpath("//H5/../ul/li");
	By logoutlink = By.linkText("Logout");
	By searchField = By.cssSelector("div#search input[name=\"search\"]");
	By searchIcon = By.cssSelector("div#search button");

	// Constructor

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// Actions

	public String getAccountPageTitle() {
		return eleutil.waitForTitleToBe(AppConstants.HOME_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIMEOUT);
	}

	public Boolean isLogoutLinkExits() {
		return eleutil.waitForElementVisible(logoutlink, AppConstants.SMALL_DEFAULT_TIMEOUT).isDisplayed();
	}

	public List<String> footerList() {
		// return eleutil.waitForElementsToBeVisible(footerlsit,
		// AppConstants.SMALL_DEFAULT_TIMEOUT);

		List<String> footerlist = eleutil.getAllElementsTextList(footerlsit, AppConstants.SMALL_DEFAULT_TIMEOUT);
		System.out.println(footerlist);
		return footerlist;
	}

	public List<String> footerHeaderList() {
		List<String> footerHeaderList = eleutil.getAllElementsTextList(footerheaderlist,
				AppConstants.SMALL_DEFAULT_TIMEOUT);
		System.out.println(footerHeaderList);
		return footerHeaderList;
	}

	public SearchResultPage doSearch(String productname) {
		System.out.println("Searching for :" +productname);
		eleutil.doSendKeysWithWait(searchField, AppConstants.SMALL_DEFAULT_TIMEOUT, productname);
		eleutil.doClick(searchIcon);
		return new SearchResultPage(driver);
		
	}
}

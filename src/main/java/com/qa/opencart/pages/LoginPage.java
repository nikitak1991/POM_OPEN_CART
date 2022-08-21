package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// By locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By forgottenpasswordlink = By.linkText("Forgotten Password");
	private By loginbutton = By.xpath("//input[@type='submit' and @value='Login']");
	private By registrationlink = By.xpath("//div[@class='list-group']//a[text()='Register']");

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// Page Actions
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIMEOUT);
		System.out.println(title);
		return title;
	}

	@Step("Getting login page URL")
	public String getLoginUrl() {
		String url = eleutil.waitForUrl(AppConstants.SMALL_DEFAULT_TIMEOUT, AppConstants.LOGIN_URL_FRACTION);
		System.out.println(url);
		return url;
	}

	public boolean isForgotPasswordLinkExit() {
		return (eleutil.waitForElementVisible(forgottenpasswordlink, AppConstants.MEDIUM_DEFAULT_TIMEOUT))
				.isDisplayed();

	}

	@Step("doing login with username :{0} and password :{1}")
	public AccountPage doLogin(String username, String password) {
		eleutil.doSendKeysWithWait(email, AppConstants.SMALL_DEFAULT_TIMEOUT, username);
		eleutil.doSendKeys(this.password, password);
		eleutil.doClick(loginbutton);
		return new AccountPage(driver);

	}

	public RegistrationPage doRegistration() {
		eleutil.doClick(registrationlink);
		return new RegistrationPage(driver);
	}
}

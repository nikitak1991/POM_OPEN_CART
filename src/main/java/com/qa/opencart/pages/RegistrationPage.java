package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// By locators
	private By fname = By.id("input-firstname");
	private By lname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phone = By.id("input-telephone");
	private By pass = By.id("input-password");
	private By confirmpass = By.id("input-confirm");
	private By subscribeyes = By.xpath("(//input[@name='newsletter'])[1]");
	private By subscribeno = By.xpath("(//input[@name='newsletter'])[2]");
	private By agreecheckbox = By.xpath("//div[text()='I have read and agree to the ']//input[@type='checkbox']");
	private By continuebutton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By successmsg = By.cssSelector("div#content h1");
	private By registrationlink = By.xpath("//div[@class='list-group']//a[text()='Register']");
	private By logout = By.linkText("Logout");

	// constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public boolean fillRegistrationForm(String fname, String lname, String email, String phoneno, String password,
			String subscribe) {
		eleutil.doSendKeysWithWait(this.fname, AppConstants.SMALL_DEFAULT_TIMEOUT, fname);
		eleutil.doSendKeys(this.lname, lname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(phone, phoneno);
		eleutil.doSendKeys(pass, password);
		eleutil.doSendKeys(confirmpass, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscribeyes);
		} else {
			eleutil.doClick(subscribeno);
		}

		eleutil.doClick(agreecheckbox);
		eleutil.doClick(continuebutton);
		String successmsg = eleutil.waitForElementVisible(this.successmsg, AppConstants.MEDIUM_DEFAULT_TIMEOUT)
				.getText();
		System.out.println("===="+successmsg+"=====");
		if (successmsg.equalsIgnoreCase(AppConstants.REGIATRATION_SUCCESS_MSG)) {
			eleutil.doClick(logout);
			eleutil.doClick(registrationlink);
			return true;
		}
		return false;

	}

	public String getRegistrationPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstants.REGISTRATION_PAGE_TITLE,
				AppConstants.SMALL_DEFAULT_TIMEOUT);
		System.out.println(title);
		return title;
	}
}

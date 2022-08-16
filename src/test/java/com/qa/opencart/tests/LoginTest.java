package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

public class LoginTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void forgottenPasswordLinkTest() {
		Assert.assertTrue(loginpage.isForgotPasswordLinkExit());
	}

	@Test
	public void loginTest() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountpage = loginpage.doLogin(username, password);
		//return new AccountPage(driver);
		 Assert.assertEquals(accountpage.getAccountPageTitle(),AppConstants.HOME_PAGE_TITLE);
	}

}

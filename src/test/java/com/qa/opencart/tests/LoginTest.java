package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Epic("Epic-100: Design the login feature")
@Story("Jira No---:Login feature with forgot password link")
public class LoginTest extends BaseTest {

	
	@Description("Story no : this test is verifyting login page title")
	@Severity(SeverityLevel.NORMAL)
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

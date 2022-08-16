package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetUp() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountpage = loginpage.doLogin(username, password);
	}

	@Test
	public void logoutLinkExits() {
		Assert.assertTrue(accountpage.isLogoutLinkExits());
	}

	@Test(enabled = false)
	public void footerListExits() {
		List<String> footercompletelist = accountpage.footerList();

	}

	@Test
	public void footerHeaderListExits() {
		List<String> actFooterHeaderList = accountpage.footerHeaderList();
		Assert.assertEquals(actFooterHeaderList, AppConstants.ACCOUNTPAGE_FOOTER_HEADERLIST);
	}

}

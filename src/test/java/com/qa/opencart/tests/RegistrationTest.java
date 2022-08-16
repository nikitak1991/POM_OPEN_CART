package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.ExcelUtil;

public class RegistrationTest extends BaseTest {
	
	@BeforeClass
	public void setUpRegistrationPage() {
		 regispage = loginpage.doRegistration();
	}
	
	
	public String randomClass() {
		Random random = new Random();
		String email ="automation"+ random.nextInt(1000)+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] regDataFromExcel(){
		Object regData[][] =ExcelUtil.readDataFromExcel("registration");
		return regData;
		
	}
	
	@Test(dataProvider="regDataFromExcel")
	public void verifyRegistrationForm(String fname,String lname,String phoneno,String password)  {
		Boolean successflag = regispage.fillRegistrationForm(fname,lname,randomClass(),phoneno,password,"yes");
		Assert.assertTrue(successflag); 
	}
	
	@Test
	public void verifyRegistrationPageTitle() {
		String actualtitle =regispage.getRegistrationPageTitle();
		Assert.assertEquals(actualtitle, "Register Account");
	}
}

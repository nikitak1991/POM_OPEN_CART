package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductSearchTest extends BaseTest {

	@BeforeClass
	public void productSearchSetUp() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountpage = loginpage.doLogin(username, password);

	}

	@Test
	public void verifyAddToCartIcon() {
		searchResPage = accountpage.doSearch("iphone");
		productdetailpage = searchResPage.selectProduct("iPhone");
		Assert.assertTrue(productdetailpage.isAddCartIconExits());
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook","MacBook Air"},
			{"MacBook","MacBook Pro"},
			{"iPhone","iPhone"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getSearchData" )
	public void verifyProductHeaderValue(String searchkey, String productname) {
		searchResPage = accountpage.doSearch(searchkey);
		productdetailpage = searchResPage.selectProduct(productname);
		String actualheadervalue = productdetailpage.productHeaderValue();
		Assert.assertEquals(actualheadervalue,productname);
		
	}
	
	@Test
	public void verifyProductMetadata() {
		searchResPage = accountpage.doSearch("MacBook");
		productdetailpage = searchResPage.selectProduct("MacBook Pro");
	    Map<String,String> meatdata = productdetailpage.getProductInfo();
	    softassert.assertEquals(meatdata.get("Brand"), "Apple");
	    softassert.assertEquals(meatdata.get("Product Code"), "Product 18");
}
}

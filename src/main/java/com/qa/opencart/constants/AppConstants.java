package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public  static final String LOGIN_PAGE_TITLE = "Account Login";
	public  static final String HOME_PAGE_TITLE = "My Account";
	public  static final String REGISTRATION_PAGE_TITLE = "Register Account";
	
	
	public static final String LOGIN_URL_FRACTION = "route=account/login";
	public static final String HOME_URL_FRACTION = "route=account/account";
	
	public static final Integer SMALL_DEFAULT_TIMEOUT = 5;
	public static final Integer MEDIUM_DEFAULT_TIMEOUT = 10;
	public static final Integer LARGE_DEFAULT_TIMEOUT = 15;
	
	public static  List<String> ACCOUNTPAGE_FOOTER_HEADERLIST =
			Arrays.asList("Information","Customer Service","Extras","My Account");
	
	public static final String REGIATRATION_SUCCESS_MSG = "Your Account Has Been Created!";
}

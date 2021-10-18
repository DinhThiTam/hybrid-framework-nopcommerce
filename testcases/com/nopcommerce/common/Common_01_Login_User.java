package com.nopcommerce.common;

import java.util.Set;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.nopcommerce.data.Customers.NewAddress;

import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.SearchPO;
import utilities.DataUtil;

public class Common_01_Login_User extends BaseTest{
	WebDriver driver;
	public static String emailAddress, password, firstName, lastName;
	public static Set<Cookie> loginPageCookie;
	String projectLocation = System.getProperty("user.dir");
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String appURL) {
		log.info("Pre-Condition - Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		fakeData = DataUtil.getData();
		
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();

		log.info("Common_01 - Step 01: Verify Home Page is displayed");
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("Register_03 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", NewAddress.FIRST_NAME);
		
		log.info("Register_03 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", NewAddress.LAST_NAME);
		
		log.info("Register_03 - Step 03: Enter valid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Register_03 - Step 04: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", password);
		
		log.info("Register_03 - Step 05: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", password);
		
		log.info("Register_03 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_03 - Step 02: Verify success messages is displayed in mandantory fields");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
		
		log.info("Register_03 - Step 03: Open 'Logout' page on header");
		registerPage.openMenuHeaderPageByClass(driver, "ico-logout");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Pre-Condition - Step 02: Open 'Login' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
		
		log.info("Login_06 - Step 01: Enter valid email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Login_06 - Step 02: Enter valid password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", password);
		
		log.info("Login_06 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Common_01  - Step 17: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
		log.info("Common_01  - Step 18: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	
	HomePO homePage;
	LoginPO loginPage;
	RegisterPO registerPage;
	SearchPO searchPage;
	MyAccountPO myInfoPage;
	DataUtil fakeData;
}

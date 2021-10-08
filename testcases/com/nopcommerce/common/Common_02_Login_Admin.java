package com.nopcommerce.common;

import java.util.Set;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.SearchPO;
import utilities.DataUtil;

public class Common_02_Login_Admin extends BaseTest{
	WebDriver driver;
	public static String emailAddress, password;
	public static Set<Cookie> loginPageCookie;
	String projectLocation = System.getProperty("user.dir");
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String appURL) {
		log.info("Pre-Condition - Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		emailAddress = "admin@yourstore.com";
		password = "admin";

		log.info("Common_01 - Step 01: Verify Home Page is displayed");
		loginPage = PageGenerator.getLoginPage(driver);
		verifyTrue(loginPage.isLoginPageTitleAdminDisplayed());
	
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

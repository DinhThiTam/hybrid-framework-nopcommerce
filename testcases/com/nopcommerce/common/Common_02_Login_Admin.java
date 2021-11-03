package com.nopcommerce.common;

import java.util.Set;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import commons.BaseTest;
import pageObject.admin.nopCommerce.DashboardPO;
import pageObject.admin.nopCommerce.LoginPO;
import pageObject.admin.nopCommerce.PageGenerator;


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
		loginPage =PageGenerator.getLoginPage(driver);
	
		log.info("Login_06 - Step 01: Enter valid email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Login_06 - Step 02: Enter valid password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", password);
		
		log.info("Login_06 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		
		log.info("Common_01  - Step 17: Verify Home Page is displayed");
		verifyTrue(dashboardPage.isDashboardPageContentDisplayed());
		
		log.info("Common_01  - Step 18: Get all login page cookies");
		loginPageCookie = dashboardPage.getAllCookies(driver);
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	
	
	LoginPO loginPage;
	DashboardPO dashboardPage;
}

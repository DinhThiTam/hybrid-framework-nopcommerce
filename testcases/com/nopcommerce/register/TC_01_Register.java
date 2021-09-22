package com.nopcommerce.register;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.RegisterPO;


public class TC_01_Register extends BaseTest {
String projectLocation = System.getProperty("user.dir");
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
	}
	@Test
	public void Register_01_Empty_Data() {
		log.info("Register_01 - Step 01: Enter empty info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", "");
		
		log.info("Register_01 - Step 02: Enter empty info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", "");
		
		log.info("Register_01 - Step 03: Enter empty info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", "");
		
		log.info("Register_01 - Step 03: Enter empty info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", "");
		
		log.info("Register_01 - Step 03: Enter empty info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", "");
		
		log.info("Register_01 - Step 03: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_01 - Step 04: Verify all error messages are displayed in mandantory fields");
		verifyTrue(registerPage.isMessageRegisterDisplayed(driver, "FirstName-error", "First name is required."));
		verifyTrue(registerPage.isMessageRegisterDisplayed(driver, "LastName-error","Last name is required."));
		verifyTrue(registerPage.isMessageRegisterDisplayed(driver, "Email-error","Email is required."));
		verifyTrue(registerPage.isMessageRegisterDisplayed(driver, "Password-error", "Password is required."));
		verifyTrue(registerPage.isMessageRegisterDisplayed(driver, "ConfirmPassword-error","Password is required."));
	}
	
	@Test
	public void Sort_0_Name_Descending() {
	
	}
	
	@Test
	public void Sort_01_Price_Ascending() {
	
	}
	
	@Test
	public void Sort_01_Price_Descending() {
		
	}

	
	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	WebDriver driver;
	HomePO homePage;
	RegisterPO registerPage;
	LoginPO loginPage;

}

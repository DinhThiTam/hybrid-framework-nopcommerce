package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.RegisterPO;
import pageUIs.nopCommerce.HomePageUI;
import utilities.DataUtil;

public class TC_01_Login extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, validEmailAddress, invalidEmailAddress,validPassword, invalidPassword;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		fakeData = DataUtil.getData();
		
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		validEmailAddress = fakeData.getEmailAddress();
		invalidEmailAddress = "abc";
		validPassword = fakeData.getPassword();
		invalidPassword = "123";
		
		log.info("Pre-Condition - Step 02: Open 'Login' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
	}
	@Test
	public void Login_01_Login_Empty_Data() {
		log.info("Login_01 - Step 01: Login to system with empty email");
		loginPage.loginToSystem("", "");
		
		log.info("Login_01 - Step 02: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isEmailEmptyMessageDisplay());
	}
	
	@Test
	public void Login_02_Login_Invalid_Email() {
		log.info("Login_02 - Step 01: Login to system with invalid email");
		loginPage.loginToSystem(invalidEmailAddress, validPassword);
		
		log.info("Login_02 - Step 02: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isEmailInvalidMessageDisplay());
	}
	
	@Test
	public void Login_03_Login_Not_Register_Email() {
		log.info("Login_02 - Step 01: Login to system with an unregistered email");
		loginPage.loginToSystem("abc@mail.vn", validPassword);
		
		log.info("Login_02 - Step 02: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isEmailNotRegisterMessageDisplay());
	}
	@Test
	public void Login_04_Register_Success() {
		log.info("Login_04 - Step 01: Open Register page on header");
		loginPage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("Login_04 - Step 02: Register to system with all valid the info");
		registerPage.registerToSystem(validEmailAddress, validPassword, validPassword, firstName, lastName);
		
		log.info("Login_04 - Step 03: Verify success messages is displayed in mandantory fields");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
		
		log.info("Login_04 - Step 04: Click to Logout button on header");
		registerPage.openMenuHeaderPageByClass(driver, "ico-logout");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Login_04 - Step 01: Open 'Login' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
	}
	@Test
	public void Login_05_Empty_Password() {
		log.info("Login_05 - Step 01: Login to system with empty password");
		loginPage.loginToSystem(validEmailAddress, "");
		
		log.info("Login_02 - Step 02: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isPasswordEmptyMessageDisplay());
	}
	
	@Test
	public void Login_06_Login_Invalid_Password() {
		log.info("Login_06 - Step 01: Login to system with invalid password");
		loginPage.loginToSystem(validEmailAddress, invalidPassword);
		
		log.info("Login_06 - Step 02: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isPasswordInvalidMessageDisplay());
	}
	
	@Test
	public void Login_07_Login_Success() {
		log.info("Login_06 - Step 01: Login to system with valid info");
		homePage = loginPage.loginToSystem(validEmailAddress, validPassword);
		
		log.info("Login_06 - Step 02: Verify Logout link is displayed on header");
		verifyTrue(homePage.isLogoutLinkDisplay());
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
}
	WebDriver driver;
	HomePO homePage;
	LoginPO loginPage;
	RegisterPO registerPage;
	DataUtil fakeData;
}

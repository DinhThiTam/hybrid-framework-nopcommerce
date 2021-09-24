package com.nopcommerce.search;

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

public class TC_01_Advanced_Search extends BaseTest {
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
		
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("Register_03 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Register_03 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Register_03 - Step 03: Enter valid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Register_03 - Step 04: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Register_03 - Step 05: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", validPassword);
		
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
	}
	@Test
	public void Login_01_Login_Empty_Data() {
		log.info("Login_01 - Step 01: Enter empty info to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", "");
		
		log.info("Login_01 - Step 02: Enter empty info to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", "");
		
		log.info("Login_01 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_01 - Step 04: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isEmailEmptyMessageDisplay());
	}
	
	@Test
	public void Login_02_Login_Invalid_Email() {
		log.info("Login_02 - Step 01: Enter invalid email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", invalidEmailAddress);
		
		log.info("Login_02 - Step 02: Enter invalid password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Login_02 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_02 - Step 04: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isEmailInvalidMessageDisplay());
	}
	
	@Test
	public void Login_03_Login_Not_Register_Email() {
		log.info("Login_03 - Step 01: Enter an unregistered email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", "abc@mail.vn");
		
		log.info("Login_03 - Step 02: Enter valid password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Login_03 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_03 - Step 04: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isEmailNotRegisterMessageDisplay());
	}
	
	@Test
	public void Login_04_Empty_Password() {
		log.info("Login_04 - Step 01: Enter valid email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Login_04 - Step 02: Enter empty password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", "");
		
		log.info("Login_04 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_04 - Step 04: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isPasswordEmptyMessageDisplay());
	}
	
	@Test
	public void Login_05_Login_Invalid_Password() {
		log.info("Login_05 - Step 01: Enter valid email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Login_05 - Step 02: Enter invalid password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", invalidPassword);
		
		log.info("Login_05 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_05 - Step 04: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isPasswordInvalidMessageDisplay());
	}
	
	@Test
	public void Login_06_Login_Success() {
		log.info("Login_06 - Step 01: Enter valid email to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Login_06 - Step 02: Enter valid password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Login_06 - Step 03: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_06 - Step 04: Verify Logout link is displayed on header");
		verifyTrue(loginPage.isLogoutLinkDisplay(driver, "ico-logout"));
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

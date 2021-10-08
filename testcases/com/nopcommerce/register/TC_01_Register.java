package com.nopcommerce.register;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import utilities.DataUtil;


public class TC_01_Register extends BaseTest {
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
		invalidEmailAddress = "abc@123.456";
		validPassword = fakeData.getPassword();
		invalidPassword = "123";
		
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
		
		log.info("Register_01 - Step 04: Enter empty info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", "");
		
		log.info("Register_01 - Step 05: Enter empty info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", "");
		
		log.info("Register_01 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_01 - Step 02: Verify all error messages are displayed in mandantory fields");
		verifyTrue(registerPage.isFirstnameEmptyInvalidMessageDisplayed());
		verifyTrue(registerPage.isLastnameEmptyInvalidMessageDisplayed());
		verifyTrue(registerPage.isEmailEmptyMessageDisplayed());
		verifyTrue(registerPage.isPasswordEmptyInvalidMessageDisplayed());
		verifyTrue(registerPage.isConfirmPasswordEmptyInvalidMessageDisplayed());
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		log.info("Register_02 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Register_02 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Register_02 - Step 03: Enter invalid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", invalidEmailAddress);
		
		log.info("Register_02 - Step 04: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Register_02 - Step 05: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", validPassword);
		
		log.info("Register_02 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_02 - Step 02: Verify email error messages is displayed in mandantory fields");
		verifyTrue(registerPage.isEmailInvalidMessageDisplayed());
	}
	
	@Test
	public void Register_03_Valid_Infomation() {
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
		
		log.info("Register_03 - Step 04: Open 'Register' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
	}
	
	@Test
	public void Register_04_Exist_Email() {
		log.info("Register_03 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Register_03 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Register_03 - Step 03: Enter an already existing email to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Register_03 - Step 04: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Register_03 - Step 05: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", validPassword);
		
		log.info("Register_03 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_04 - Step 02: Verify email error messages is displayed in mandantory fields");
		verifyTrue(registerPage.isEmailExistMessageDisplayed());
	}
	
	@Test(description = "Password less than 6 chars")
	public void Register_05_InValid_Password() {
		log.info("Register_03 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Register_03 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Register_03 - Step 03: Enter an already existing email to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Register_03 - Step 04: Enter invalid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", invalidPassword);
		
		log.info("Register_03 - Step 05: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", invalidPassword);
		
		log.info("Register_03 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_05 - Step 02: Verify password error messages is displayed in mandantory fields");
		verifyTrue(registerPage.isPasswordInvalidMessageDisplayed());
	}
	
	@Test
	public void Register_06_Not_Match_Password() {
		log.info("Register_03 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Register_03 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Register_03 - Step 03: Enter an already existing email to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Register_03 - Step 04: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Register_03 - Step 05: Enter invalid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", invalidPassword);
		
		log.info("Register_03 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_06 - Step 02: Verify confirm password error messages is displayed in mandantory fields");
		verifyTrue(registerPage.isConfirmPasswordInvalidMessageDisplayed());
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
	DataUtil fakeData;

}

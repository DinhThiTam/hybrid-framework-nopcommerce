package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import utilities.DataUtil;


public class TC_01_Customer_Info extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, validEmailAddress, invalidEmailAddress,validPassword, invalidPassword, birthDay, birthMonth, birthYear, companyName;
	
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
		birthDay = "1";
		birthMonth = "January";
		birthYear = "1999";
		companyName= "Automation FC";
		
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("Pre-Condition - Step 03: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Pre-Condition - Step 04: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Pre-Condition - Step 05: Enter valid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", validEmailAddress);
		
		log.info("Pre-Condition - Step 06: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", validPassword);
		
		log.info("Pre-Condition - Step 07: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", validPassword);
		
		log.info("Pre-Condition - Step 08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Pre-Condition - Step 09: Verify success messages is displayed in mandantory fields");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
	
	}
	@Test
	public void My_Account_01_Customer_Info() {
		log.info("My_Account_01 - Step 01: Open 'My account' page on header");
		registerPage.openMenuHeaderPageByClass(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("My_Account_01 - Step 02: Open 'Customer Info' form");
		myAccountPage.openTabMenuByName("Customer info");
		
		log.info("My_Account_01 - Step 03: Update Gender information 'Female' to radio button");
		myAccountPage.clickToRadioAndCheckboxByLabel(driver, "Female");
		
		log.info("My_Account_01 - Step 04: Update First name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "FirstName", firstName);
		
		log.info("My_Account_01 - Step 06: Update Last name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "LastName", lastName);
		
		log.info("My_Account_01 - Step 06: Update Date of birthday information to dropdown");
		myAccountPage.selectItemInDropdownByName(driver, birthDay, "DateOfBirthDay");
		myAccountPage.selectItemInDropdownByName(driver, birthMonth, "DateOfBirthMonth");
		myAccountPage.selectItemInDropdownByName(driver, birthYear, "DateOfBirthYear");
		
		log.info("My_Account_01 - Step 07: Update Email information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Email", validEmailAddress);
		
		log.info("My_Account_01 - Step 08: Update Company name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Company", companyName);
		
		log.info("My_Account_01 - Step 09: Click to 'Save' button");
		myAccountPage.clickToButtonByName(driver, "Save");
		
		log.info("My_Account_01 - Step 10: Verify gender infomation is updated successfully");
		verifyTrue(myAccountPage.isSelectedItemInRadio(driver, "Female"));
		
		log.info("My_Account_01 - Step 11: Verify firstname infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "FirstName"), firstName);
		
		log.info("My_Account_01 - Step 12: Verify lastname infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "LastName"), lastName);
		
		log.info("My_Account_01 - Step 13: Verify date of birth infomation is updated successfully");
		verifyEquals(myAccountPage.getSelectItemInDropdownByName(driver, "DateOfBirthDay"), birthDay);
		verifyEquals(myAccountPage.getSelectItemInDropdownByName(driver, "DateOfBirthMonth"), birthMonth);
		verifyEquals(myAccountPage.getSelectItemInDropdownByName(driver, "DateOfBirthYear"), birthYear);
		
		log.info("My_Account_01 - Step 14: Verify email infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "Email"), validEmailAddress);
		
		log.info("My_Account_01 - Step 15: Verify company name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "Company"), companyName);
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
	MyAccountPO myAccountPage;

}

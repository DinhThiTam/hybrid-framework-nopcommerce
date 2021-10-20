package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;
import com.nopcommerce.data.Customers.NewAddress;

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
		
		log.info("Pre-Condition - Step 02: Open 'Login' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
		
		log.info("Pre-Condition - Step 03: Set login page cookie");
		loginPage.setAllCookies(driver, Common_01_Login_User.loginPageCookie);
		loginPage.sleepInsecond(5);
		loginPage.refreshPage(driver);
		
		log.info("Pre-Condition - Step 04: Open homepage");
		homePage =  loginPage.openHomePage();
		
		log.info("Pre-Condition - Step 05: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	
	}
	@Test
	public void My_Account_01_Customer_Info() {
		log.info("My_Account_01 - Step 01: Open 'My account' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("My_Account_01 - Step 02: Open 'Customer Info' form");
		myAccountPage.openTabMenuCustomerInfo();
		myAccountPage.sleepInsecond(3);
		
		log.info("My_Account_01 - Step 03: Update Gender information 'Female' to radio button");
		myAccountPage.clickToRadioAndCheckboxByLabel(driver, "Female");
		
		log.info("My_Account_01 - Step 04: Update First name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "FirstName", NewAddress.FIRST_NAME);
		
		log.info("My_Account_01 - Step 06: Update Last name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "LastName", NewAddress.LAST_NAME);
		
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
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "FirstName", "value"), NewAddress.FIRST_NAME);
		
		log.info("My_Account_01 - Step 12: Verify lastname infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "LastName", "value"), NewAddress.LAST_NAME);
		
		log.info("My_Account_01 - Step 13: Verify date of birth infomation is updated successfully");
		verifyEquals(myAccountPage.getSelectItemInDropdownByName(driver, "DateOfBirthDay"), birthDay);
		verifyEquals(myAccountPage.getSelectItemInDropdownByName(driver, "DateOfBirthMonth"), birthMonth);
		verifyEquals(myAccountPage.getSelectItemInDropdownByName(driver, "DateOfBirthYear"), birthYear);
		
		log.info("My_Account_01 - Step 14: Verify email infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "Email", "value"), validEmailAddress);
		
		log.info("My_Account_01 - Step 15: Verify company name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByID(driver, "Company", "value"), companyName);
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

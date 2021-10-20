package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;


import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import utilities.DataUtil;


public class TC_03_Change_Password extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress,password, newPassword, birthDay, birthMonth, birthYear, companyName;
	
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
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		newPassword = fakeData.getPassword();
		
		
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("Pre-Condition - Step 03: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Pre-Condition - Step 04: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Pre-Condition - Step 05: Enter valid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Pre-Condition - Step 06: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", password);
		
		log.info("Pre-Condition - Step 07: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", password);
		
		log.info("Pre-Condition - Step 08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Pre-Condition - Step 09: Verify success messages is displayed in mandantory fields");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
	
	}
	@Test
	public void Change_Password_01() {
		log.info("Change_Password_01 - Step 01: Open 'My account' page on header");
		registerPage.openMenuHeaderPageByClass(driver, "ico-account");
		registerPage.isJQueryAjaxLoadedSuccess(driver);
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("Change_Password_01 - Step 02: Open 'Change password' form");
		myAccountPage.openTabMenuChangePasswod();
		myAccountPage.sleepInsecond(3);
		
		log.info("Change_Password_01 - Step 03: Enter Old Password information to textbox");
		myAccountPage.enterToTextboxByID(driver, "OldPassword", password);
		
		log.info("Change_Password_01 - Step 04: Update New Password information to textbox");
		myAccountPage.enterToTextboxByID(driver, "NewPassword", newPassword);
		
		log.info("Change_Password_01 - Step 05: Update Confirm New Password information to textbox");
		myAccountPage.enterToTextboxByID(driver, "ConfirmNewPassword", newPassword);
		
		log.info("Change_Password_01 - Step 06: Click to 'Change Password' button");
		myAccountPage.clickToButtonByName(driver, "Change password");
		
		log.info("Change_Password_01 - Step 07: Verify success message is displayed");
		verifyTrue(myAccountPage.isChangePasswordSuccessMessageDisplayed());
		
		log.info("Change_Password_01 - Step 08: Click to 'x' icon");
		myAccountPage.clickToCloseMessageButton();
		myAccountPage.sleepInsecond(3);
		
		log.info("Change_Password_01 - Step 09: Click to 'Log out' button");
		myAccountPage.openMenuHeaderPageByClass(driver, "ico-logout");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Change_Password_01- Step 10: Click to 'Log in' button");
		homePage.openMenuHeaderPageByClass(driver, "ico-login");
		loginPage = PageGenerator.getLoginPage(driver);
		
		log.info("Change_Password_01 - Step 11: Enter email address to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Change_Password_01 - Step 12: Enter old password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", password);
		
		log.info("Change_Password_01 - Step 13: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Change_Password_01 - Step 14: Verify error messages is displayed in mandantory fields");
		verifyTrue(loginPage.isPasswordInvalidMessageDisplay());
		
		log.info("Change_Password_01 - Step 15: Enter email address to 'Email' textbox");
		loginPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Change_Password_01 - Step 16: Enter new password to 'Password' textbox");
		loginPage.enterToTextboxByID(driver,"Password", newPassword);
		
		log.info("Change_Password_01 - Step 17: Click to 'Login' button");
		loginPage.clickToButtonByName(driver, "Log in");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Change_Password_01 - Step 18: Verify login success");
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
	RegisterPO registerPage;
	LoginPO loginPage;
	DataUtil fakeData;
	MyAccountPO myAccountPage;

}

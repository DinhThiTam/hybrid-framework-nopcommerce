package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.nopCommerce.DesktopsPO;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.MyAccountPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.ProductReviewPO;
import pageObject.nopCommerce.RegisterPO;
import utilities.DataUtil;


public class TC_04_My_Product_Review extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress,password, newPassword, birthDay, birthMonth, birthYear, companyName,titleReview, textReview;
;
	
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
		titleReview = "Automation";
		textReview = "Very good";
		
		
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
	public void My_Product_Review_01() {
		log.info("My_Account_01 - Step 01: Open sub menu 'Desktops'");
		registerPage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
		desktopsPage = PageGenerator.getDesktopsPage(driver);
		desktopsPage.clickToLinkByText(driver,"Build your own computer");
		desktopsPage.clickToLinkByText(driver,"Add your review");
		productReviewPage = PageGenerator.getReviewProductPage(driver);
		productReviewPage.enterToTextboxByID(driver, "AddProductReview_Title", titleReview);
		productReviewPage.enterToReviewTextInTextArea(textReview);
		productReviewPage.clickToButtonByName(driver, "Submit review");
		verifyEquals(productReviewPage.getAddReviewSuccessMessage(),"Product review is successfully added.");
		productReviewPage.openMenuHeaderPageByClass(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		myAccountPage.openTabMenuByName("My product reviews");
		verifyEquals(myAccountPage.getReviewTextByClass("review-title"), titleReview);
		verifyEquals(myAccountPage.getReviewTextByClass("review-text"), textReview);

		
		
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
	DesktopsPO desktopsPage;
	ProductReviewPO productReviewPage;

}

package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;

import commons.BaseTest;
import pageObject.user.nopCommerce.DesktopsPO;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.ProductDetailsPO;
import pageObject.user.nopCommerce.ProductReviewPO;
import pageObject.user.nopCommerce.RegisterPO;
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
		titleReview = fakeData.getTitle();
		textReview = fakeData.getString();
		
		
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
	public void My_Product_Review_01() {
		log.info("My_Product_Review_01 - Step 01: Open sub menu 'Desktops'");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
		desktopsPage = PageGenerator.getDesktopsPage(driver);
		
		log.info("My_Product_Review_01 - Step 02: Click to the product title link");
		desktopsPage.clickToProductLinkByText(driver,"Build your own computer");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("My_Product_Review_01 - Step 03: Click to 'Add your review' link");
		productDetailsPage.clickToProductLinkByText(driver,"Add your review");
		productReviewPage = PageGenerator.getReviewProductPage(driver);
		
		
		log.info("My_Product_Review_01 - Step 04: Enter the review title in the textbox ");
		productReviewPage.enterToTextboxByID(driver, "AddProductReview_Title", titleReview);
		
		log.info("My_Product_Review_01 - Step 05: Enter the review text in the textarea ");
		productReviewPage.enterToReviewTextInTextArea(textReview);
		
		log.info("My_Product_Review_01 - Step 05: Click to 'Submit review' button ");
		productReviewPage.clickToButtonByName(driver, "Submit review");
		
		log.info("My_Product_Review_01 - Step 06: Verify success message is displayed");
		verifyEquals(productReviewPage.getAddReviewSuccessMessage(),"Product review is successfully added.");
		
		log.info("My_Product_Review_01 - Step 07: Open My account page on header");
		productReviewPage.openMenuHeaderPageByClass(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("My_Product_Review_01 - Step 08: Open tab menu 'My product reviews'");
		//myAccountPage.openTabMenuByName(driver, "My product reviews");
		
		log.info("My_Product_Review_01 - Step 09: Verify review is displayed");
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
	ProductDetailsPO productDetailsPage;

}

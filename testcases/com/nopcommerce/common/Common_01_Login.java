package com.nopcommerce.common;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.MyAccountPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.RegisterPO;
import pageObject.nopCommerce.SearchPO;
import pageObjects.user.nopCommerce.CustomerInfoPageObject;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.OrderPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;

public class Common_01_Login extends BaseTest{
	WebDriver driver;
	String emailAddress, password;
	public static Set<Cookie> loginPageCookie;
	String projectLocation = System.getProperty("user.dir");
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String appURL) {
		log.info("Pre-Condition - Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		
		driver = getBrowserDriver(browserName, appURL);
		emailAddress = getRandomEmail();
		password = "123456";

		log.info("Common_01 - Step 01: Verify Home Page is displayed");
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
		log.info("Common_01  - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Common_01  - Step 03: Click to Male radio button");
		registerPage.clickToGenderRadioButton();
		
		log.info("Common_01  - Step 04: Click to First Name textbox");
		registerPage.enterToFirstNameTextbox("dinh");
		
		log.info("Common_01  - Step 05: Click to Last Name textbox");
		registerPage.enterToLastNameTextbox("tam");
		
		log.info("Common_01  - Step 06: Click to Email textbox with value: " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("Common_01  - Step 07: Click to Password textbox with value: " + password);
		registerPage.enterToPasswordTextbox(password);
		
		log.info("Common_01  - Step 08: Click to Confirm Password textbox with value: " + password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		log.info("Common_01  - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Common_01  - Step 10: Verify success message is displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());log.info("Pre-Condition - Step 02: Open Register page on header");
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
		
		log.info("Common_01  - Step 12: Verify  Home Page is displayed ");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	
		
		log.info("Common_01  - Step 14: Enter to Email textbox with value: " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);
		
		log.info("Common_01  - Step 15: Enter to Password textbox with value: " + password);
		loginPage.enterToPasswordTextbox(password);
		
		log.info("Common_01  - Step 16: Click to Login button");
		homePage= loginPage.clickToLoginButton();
		
		log.info("Common_01  - Step 17: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
		log.info("Common_01  - Step 18: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	
	
	public String getRandomEmail() {
		Random rand = new Random();
		rand.nextInt(99999);
		return "testing" + rand.nextInt(99999) + "@email.com";
	}
	HomePO homePage;
	LoginPO loginPage;
	RegisterPO registerPage;
	SearchPO searchPage;
	MyAccountPO myInfoPage;
}

package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class TC_02_Add_Addresses extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, companyName, fullName, citySateZip, stateProvince, countryName, cityName, address1, address2, zipCode, phoneNumber, faxNumber;
	
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
		companyName= "Automation FC";
		fullName = firstName + " " + lastName;
		companyName= "Automation FC";
		stateProvince= "Other"; 
		countryName="Viet Nam" ;
		cityName= "Da Nang";
		address1="123/04 Le Lai" ;
		address2= "234/05 Hai Phong"; 
		zipCode= "550000"; 
		citySateZip = cityName + ", " + zipCode;
		phoneNumber= "0123456789"; 
		faxNumber = "0983970447";
		
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
	public void Add_Address_01() {
		log.info("My_Account_01 - Step 01: Open 'My account' page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("Add_Address_01 - Step 02: Open 'Addresses' form");
		myAccountPage.openTabMenuByName("Addresses");
		myAccountPage.sleepInsecond(2);
		
		log.info("Add_Address_01 - Step 03: Click to 'Add new' button");
		myAccountPage.clickToButtonByName(driver, "Add new");
		
		log.info("Add_Address_01 - Step 04: Update First name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_FirstName", NewAddress.FIRST_NAME);
		
		log.info("Add_Address_01 - Step 05: Update Last name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_LastName", NewAddress.LAST_NAME);
		
		log.info("Add_Address_01 - Step 06: Update Email information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Email", emailAddress);
		
		log.info("Add_Address_01 - Step 07: Update Company name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Company", companyName);
		
		log.info("Add_Address_01 - Step 03: Update Country name information to dropdown");
		myAccountPage.selectItemInDropdownByName(driver,countryName , "Address.CountryId");
	
		log.info("Add_Address_01 - Step 03: Update State province information to dropdown");
		myAccountPage.selectItemInDropdownByName(driver, stateProvince, "Address_StateProvinceId");
		
		log.info("Add_Address_01 - Step 03: Update Company City name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_City", cityName);
		
		log.info("Add_Address_01 - Step 03: Update Address 1 information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Address1", address1);
		
		log.info("Add_Address_01 - Step 03: Update Address 2 information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Address2", address2);
		
		log.info("Add_Address_01 - Step 03: Update Zip code information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_ZipPostalCode", zipCode);
		
		log.info("Add_Address_01 - Step 03: Update Phone number information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);
		
		log.info("Add_Address_01 - Step 03: Click to 'Save' button");
		myAccountPage.clickToButtonByName(driver, "Save");
		
		log.info("Add_Address_01 - Step 03: Verify full name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("name"), fullName);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("email"), "Email: " + emailAddress);
		
		log.info("Add_Address_01 - Step 03: Verify phone number infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("Address_PhoneNumber"), "Phone number: " + phoneNumber);
		
		log.info("Add_Address_01 - Step 03: Verify company name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("company"), companyName);
		
		log.info("Add_Address_01 - Step 03: Verify adress 1 infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("address1"), address1);
		
		log.info("Add_Address_01 - Step 03: Verify adress 2 infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass( "address2"), address2);
		
		log.info("Add_Address_01 - Step 03: Verify country name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("country"), countryName);
		
		log.info("Add_Address_01 - Step 03: Verify city state zip infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("city-state-zip"), citySateZip);
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

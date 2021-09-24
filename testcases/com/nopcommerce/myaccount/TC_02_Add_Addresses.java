package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.MyAccountPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.RegisterPO;
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
	public void My_Account_02_Add_Address() {
		log.info("My_Account_01 - Step 01: Open 'My account' page on header");
		registerPage.openMenuHeaderPageByClass(driver, "ico-account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		log.info("My_Account_01 - Step 02: Open 'Addresses' form");
		myAccountPage.openTabMenuByName("Addresses");
		myAccountPage.sleepInsecond(2);
		
		log.info("My_Account_01 - Step 03: Click to 'Add new' button");
		myAccountPage.clickToButtonByName(driver, "Add new");
		
		log.info("My_Account_01 - Step 03: Update First name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_FirstName", firstName);
		
		log.info("My_Account_01 - Step 03: Update Last name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_LastName", lastName);
		
		log.info("My_Account_01 - Step 03: Update Email information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Email", emailAddress);
		
		log.info("My_Account_01 - Step 03: Update Company name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Company", companyName);
		
		log.info("My_Account_01 - Step 03: Update Country name information to dropdown");
		myAccountPage.selectItemInDropdownByName(driver,countryName , "Address_CountryId");
	
		log.info("My_Account_01 - Step 03: Update State province information to dropdown");
		myAccountPage.selectItemInDropdownByName(driver, stateProvince, "Address_StateProvinceId");
		
		log.info("My_Account_01 - Step 03: Update Company City name information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_City", cityName);
		
		log.info("My_Account_01 - Step 03: Update Address 1 information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Address1", address1);
		
		log.info("My_Account_01 - Step 03: Update Address 2 information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_Address2", address2);
		
		log.info("My_Account_01 - Step 03: Update Zip code information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_ZipPostalCode", zipCode);
		
		log.info("My_Account_01 - Step 03: Update Phone number information to textbox");
		myAccountPage.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);
		
		log.info("My_Account_01 - Step 03: Click to 'Save' button");
		myAccountPage.clickToButtonByName(driver, "Save");
		
		log.info("My_Account_01 - Step 03: Verify full name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("name"), fullName);
		
		log.info("My_Account_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("email"), "Email: " + emailAddress);
		
		log.info("My_Account_01 - Step 03: Verify phone number infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("Address_PhoneNumber"), "Phone number: " + phoneNumber);
		
		log.info("My_Account_01 - Step 03: Verify company name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("company"), companyName);
		
		log.info("My_Account_01 - Step 03: Verify adress 1 infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("address1"), address1);
		
		log.info("My_Account_01 - Step 03: Verify adress 2 infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass( "address2"), address2);
		
		log.info("My_Account_01 - Step 03: Verify country name infomation is updated successfully");
		verifyEquals(myAccountPage.getTextboxValueByClass("country"), countryName);
		
		log.info("My_Account_01 - Step 03: Verify city state zip infomation is updated successfully");
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

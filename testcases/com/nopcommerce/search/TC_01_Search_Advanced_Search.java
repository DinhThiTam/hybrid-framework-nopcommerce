package com.nopcommerce.search;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.SearchPO;
import pageUIs.nopCommerce.HomePageUI;
import utilities.DataUtil;

public class TC_01_Search_Advanced_Search extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, validEmailAddress, emailAddress,password,dataNotExist, dataRelative, dataAbsolute, dataAdvanceSearch;
	
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
		dataNotExist = "Macbook Pro 2050";
		dataRelative = "Lenovo";
		dataAbsolute = "ThinkPad X1 Carbon";
		dataAdvanceSearch = "Apple MacBook Pro";
		
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("Register_03 - Step 01: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver,"FirstName", firstName);
		
		log.info("Register_03 - Step 02: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver,"LastName", lastName);
		
		log.info("Register_03 - Step 03: Enter valid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver,"Email", emailAddress);
		
		log.info("Register_03 - Step 04: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver,"Password", password);
		
		log.info("Register_03 - Step 05: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver,"ConfirmPassword", password);
		
		log.info("Register_03 - Step 06: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");
		
		log.info("Register_03 - Step 02: Verify success messages is displayed in mandantory fields");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
	}
	@Test
	public void Search_01_Empty_Data() {
		log.info("Pre-Condition - Step 02: Open Search page on footer");
		registerPage.openMenuFooterPageByName(driver,"Search");
		searchPage = PageGenerator.getSearchPage(driver);
		
		log.info("Login_01 - Step 02: Enter empty data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", "");
		
		log.info("Login_01 - Step 03: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		searchPage.sleepInsecond(3);
		
		log.info("Login_01 - Step 04: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("Search term minimum length is 3 characters"));
	}
	
	@Test
	public void Search_02_Not_Exist_Data() {
		log.info("Login_01 - Step 02: Enter not exist data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataNotExist);
		
		log.info("Login_01 - Step 03: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("Login_01 - Step 04: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_03_Product_Name_Relative() {
		log.info("Login_01 - Step 02: Enter relative data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataRelative);
		
		log.info("Login_01 - Step 03: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("Login_01 - Step 04: Verify 2 product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Lenovo IdeaCentre 600 All-in-One PC"));
		verifyTrue(searchPage.isProductDisplayedByTitle("Lenovo Thinkpad X1 Carbon Laptop"));
		
		log.info("Login_01 - Step 04: Get the product size by 2");
		verifyEquals(searchPage.getProductSize(driver), 2);
	}
	@Test
	public void TC_04_Product_Name_Absolute() {
		log.info("Login_01 - Step 02: Enter absolute data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataAbsolute);
		
		log.info("Login_01 - Step 03: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("Login_01 - Step 04: Verify only one product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Lenovo Thinkpad X1 Carbon Laptop"));
		
		log.info("Login_01 - Step 04: Get the product size by 1");
		verifyEquals(searchPage.getProductSize(driver), 1);
	}
	
	@Test
	public void TC_05_Parent_Categories() {
		log.info("TC_05 - Step 01: Enter keyword to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataAdvanceSearch);
		
		log.info("TC_05 - Step 02: Check to 'Advanced Search' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Advanced search");
		
	
		log.info("TC_05 - Step 03: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_05 - Step 04:Select item in dropdown");
		searchPage.selectItemInDropdownByName(driver, "Computers", "cid");
		
		log.info("TC_05 - Step 05: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
		
		log.info("TC_05 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("TC_05 - Step 07: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_06_Product_Sub_Categories() {
		log.info("TC_05 - Step 03: Verify textbox is entered 'Apple MacBook Pro' text");
		verifyEquals(searchPage.getTextboxValueByID(driver, "q"), "Apple MacBook Pro");
		
		log.info("TC_05 - Step 03: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_05 - Step 05: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
		
		log.info("TC_05 - Step 02: Check to 'Automatically search sub categories' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("TC_05 - Step 03: Verify 'Automatically search sub categories' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Automatically search sub categories"));
		
		log.info("TC_05 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("Login_01 - Step 04: Verify only one product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Apple MacBook Pro 13-inch"));
		
		log.info("Login_01 - Step 04: Get the product size by 1");
		verifyEquals(searchPage.getProductSize(driver), 1);
	}
	
	@Test
	public void TC_07_Incorrect_Manufactuner() {
		log.info("TC_05 - Step 03: Verify textbox is entered 'Apple MacBook Pro' text");
		verifyEquals(searchPage.getTextboxValueByID(driver, "q"), "Apple MacBook Pro");
		
		log.info("TC_05 - Step 03: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_05 - Step 05: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
	
		log.info("TC_05 - Step 02: Check to 'Automatically search sub categories' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("TC_05 - Step 03: Verify 'Automatically search sub categories' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Automatically search sub categories"));
		
		log.info("TC_05 - Step 04:Select item in dropdown");
		searchPage.selectItemInDropdownByName(driver, "HP", "mid");
		
		log.info("TC_05 - Step 05: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "mid"), "HP");
		
		log.info("TC_05 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("TC_05 - Step 07: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_08_Correct_Manufactuner() {
		log.info("TC_05 - Step 03: Verify textbox is entered 'Apple MacBook Pro' text");
		verifyEquals(searchPage.getTextboxValueByID(driver, "q"), "Apple MacBook Pro");
		
		log.info("TC_05 - Step 03: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_05 - Step 05: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
	
		log.info("TC_05 - Step 02: Check to 'Automatically search sub categories' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("TC_05 - Step 03: Verify 'Automatically search sub categories' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Automatically search sub categories"));
		
		log.info("TC_05 - Step 04:Select item in dropdown");
		searchPage.selectItemInDropdownByName(driver, "Apple", "mid");
		
		log.info("TC_05 - Step 05: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "mid"), "Apple");
		
		log.info("TC_05 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByName(driver, "Search");
		
		log.info("Login_01 - Step 04: Verify only one product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Apple MacBook Pro 13-inch"));
		
		log.info("Login_01 - Step 04: Get the product size by 1");
		verifyEquals(searchPage.getProductSize(driver), 1);
	}
	
	@Test
	public void TC_09_Price_Range() {
		
	}
	
	
	@Test
	public void TC_10_Price_Range_Less_Than_Product_Range() {
		
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
	SearchPO searchPage;
}

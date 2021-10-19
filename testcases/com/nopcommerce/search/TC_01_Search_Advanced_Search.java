package com.nopcommerce.search;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;

import commons.BaseTest;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.SearchPO;
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
	public void Search_01_Empty_Data() {
		log.info("Pre-Condition - Step 01: Open Search page on footer");
		homePage.openMenuFooterPageByName(driver,"Search");
		searchPage = PageGenerator.getSearchPage(driver);
		
		log.info("Search_01 - Step 02: Enter empty data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", " ");
		
		log.info("Search_01 - Step 03: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		searchPage.sleepInsecond(3);
		
		log.info("Search_01 - Step 04: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("Search term minimum length is 3 characters"));
	}
	
	@Test
	public void Search_02_Not_Exist_Data() {
		log.info("Searc_02 - Step 01: Enter not exist data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataNotExist);
		
		log.info("Searc_02 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("Searcn_02 - Step 03: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_03_Product_Name_Relative() {
		log.info("TC_03 - Step 01: Enter relative data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataRelative);
		
		log.info("TC_03 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("TC_03 - Step 03: Verify 2 product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Lenovo IdeaCentre 600 All-in-One PC"));
		verifyTrue(searchPage.isProductDisplayedByTitle("Lenovo Thinkpad X1 Carbon Laptop"));
		
		log.info("TC_03 - Step 04: Get the product size by 2");
		verifyEquals(searchPage.getProductSize(driver), 2);
	}
	@Test
	public void TC_04_Product_Name_Absolute() {
		log.info("TC_04 - Step 01: Enter absolute data to 'Search keyword' textbox");
		searchPage.enterToTextboxByID(driver,"q", dataAbsolute);
		
		log.info("TC_04 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("TC_04 - Step 03: Verify only one product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Lenovo Thinkpad X1 Carbon Laptop"));
		
		log.info("TC_04 - Step 04: Get the product size by 1");
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
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("TC_05 - Step 07: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_06_Product_Sub_Categories() {
		log.info("TC_06 - Step 01: Verify textbox is entered 'Apple MacBook Pro' text");
		verifyEquals(searchPage.getTextboxValueByID(driver, "q", "value"), "Apple MacBook Pro");
		
		log.info("TC_06 - Step 02: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_06 - Step 03: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
		
		log.info("TC_06 - Step 04: Check to 'Automatically search sub categories' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("TC_06 - Step 05: Verify 'Automatically search sub categories' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Automatically search sub categories"));
		
		log.info("TC_06 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("Login_01 - Step 07: Verify only one product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Apple MacBook Pro 13-inch"));
		
		log.info("Login_01 - Step 08: Get the product size by 1");
		verifyEquals(searchPage.getProductSize(driver), 1);
	}
	
	@Test
	public void TC_07_Incorrect_Manufactuner() {
		log.info("TC_07 - Step 01: Verify textbox is entered 'Apple MacBook Pro' text");
		verifyEquals(searchPage.getTextboxValueByID(driver, "q", "value"), "Apple MacBook Pro");
		
		log.info("TC_07 - Step 02: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_07 - Step 03: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
	
		log.info("TC_07 - Step 04: Check to 'Automatically search sub categories' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("TC_07 - Step 05: Verify 'Automatically search sub categories' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Automatically search sub categories"));
		
		log.info("TC_07 - Step 06:Select item in dropdown");
		searchPage.selectItemInDropdownByName(driver, "HP", "mid");
		
		log.info("TC_07 - Step 07: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "mid"), "HP");
		
		log.info("TC_07 - Step 08: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("TC_07 - Step 09: Verify error messages is displayed");
		verifyTrue(searchPage.isSearchMessageDisplayedByText("No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_08_Correct_Manufactuner() {
		log.info("TC_08 - Step 01: Verify textbox is entered 'Apple MacBook Pro' text");
		verifyEquals(searchPage.getTextboxValueByID(driver, "q", "value"), "Apple MacBook Pro");
		
		log.info("TC_08 - Step 02: Verify 'Advanced Search' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Advanced search"));
		
		log.info("TC_08 - Step 03: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "cid"), "Computers");
	
		log.info("TC_08 - Step 04: Check to 'Automatically search sub categories' checkbox");
		searchPage.clickToRadioAndCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("TC_08 - Step 05: Verify 'Automatically search sub categories' is selected");
		verifyTrue(searchPage.isSelectedItemByLable(driver, "Automatically search sub categories"));
		
		log.info("TC_08 - Step 06:Select item in dropdown");
		searchPage.selectItemInDropdownByName(driver, "Apple", "mid");
		
		log.info("TC_08 - Step 07: Verify item is selected");
		verifyEquals(searchPage.getSelectItemInDropdownByName(driver, "mid"), "Apple");
		
		log.info("TC_08 - Step 08: Click to 'Search' button");
		searchPage.clickToButtonByTagClassAndText(driver, "page-body", "Search");
		
		log.info("TC_08 - Step 09: Verify only one product titles are displayed");
		verifyTrue(searchPage.isProductDisplayedByTitle("Apple MacBook Pro 13-inch"));
		
		log.info("TC_08 - Step 10: Get the product size by 1");
		verifyEquals(searchPage.getProductSize(driver), 1);
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

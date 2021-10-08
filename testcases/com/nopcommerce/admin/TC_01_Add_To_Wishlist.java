package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_02_Login_Admin;

import commons.BaseTest;
import pageObject.admin.nopCommerce.DashboardPO;
import pageObject.admin.nopCommerce.LoginPO;
import pageObject.admin.nopCommerce.PageGenerator;
import pageObject.admin.nopCommerce.ProductSearchPO;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;
import utilities.DataUtil;


public class TC_01_Add_To_Wishlist extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String productName;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		
		loginPage = PageGenerator.getLoginPage(driver);
		verifyTrue(loginPage.isLoginPageTitleAdminDisplayed());
		
//		log.info("Pre-Condition - Step 02: Set login page cookie");
//		loginPage.setAllCookies(driver, Common_02_Login_Admin.loginPageCookie);
//		loginPage.sleepInsecond(5);
//		loginPage.refreshPage(driver);
		
		log.info("Pre-Condition - Step 03: Open homepage");
		loginPage.clickToButtonByName(driver, "Log in");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		
		
	
	}
	@Test
	public void TC_01_Search_With_Product_Name() {
		log.info("Pre-Condition - Step 05: Open sub menu 'Product'");
		dashboardPage.openSubMenuPageByName(driver, "Catalog","Products");
		productSearchPage = PageGenerator.getProductSearchPage(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByID(driver, "SearchProductName", productName);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		
	
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	WebDriver driver;
	LoginPO loginPage;
	DashboardPO dashboardPage;
	ProductSearchPO productSearchPage;

}

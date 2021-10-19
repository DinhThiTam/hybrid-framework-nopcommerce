package com.nopcommerce.wishlist;

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
import pageObject.user.nopCommerce.NotebooksPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.ProductDetailsPO;
import pageObject.user.nopCommerce.RecentlyViewrdProductsPO;
import utilities.DataUtil;


public class TC_05_Recently_Viewed_Product extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress,password ;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
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
	public void Add_To_Cart_01() {
		log.info("Add_To_Cart_01 - Step 01: Open sub menu 'Notebooks'");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Notebooks ");
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Add_To_Cart_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Asus N551JK-XO076H Laptop");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("Add_To_Cart_01 - Step 03: Click to back page");
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Add_To_Cart_01 - Step 04: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Asus N551JK-XO076H Laptop");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Add_To_Cart_01 - Step 05: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Samsung Series 9 NP900X4C Premium Ultrabook");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Add_To_Cart_01 - Step 06: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"HP Spectre XT Pro UltraBook");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Add_To_Cart_01 - Step 07: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"HP Envy 6-1180ca 15.6-Inch Sleekbook");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("Add_To_Cart_01 - Step 08: Open 'Recently viewed products' page on footer menu");
		productDetailsPage.openMenuFooterPageByName(driver, "Recently viewed products");
		recentlyViewPage = PageGenerator.getRecentlyViewedProductsPage(driver);
		
		log.info("Add_To_Cart_01 - Step 09: Verify only the last three products are displayed");
		verifyTrue(recentlyViewPage.isProductDisplayedByTitle(driver, "Samsung Series 9 NP900X4C Premium Ultrabook"));
		verifyTrue(recentlyViewPage.isProductDisplayedByTitle(driver, "HP Spectre XT Pro UltraBook"));
		verifyTrue(recentlyViewPage.isProductDisplayedByTitle(driver, "HP Envy 6-1180ca 15.6-Inch Sleekbook"));
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
	DataUtil fakeData;
	NotebooksPO notebooksPage;
	DesktopsPO desktopsPage;
	ProductDetailsPO productDetailsPage;
	RecentlyViewrdProductsPO recentlyViewPage;
}

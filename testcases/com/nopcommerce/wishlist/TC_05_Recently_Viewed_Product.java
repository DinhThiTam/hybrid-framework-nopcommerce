package com.nopcommerce.wishlist;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.nopCommerce.DesktopsPO;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.MyAccountPO;
import pageObject.nopCommerce.NotebooksPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.ProductDetailsPO;
import pageObject.nopCommerce.RecentlyViewrdProductsPO;
import pageObject.nopCommerce.RegisterPO;
import pageObject.nopCommerce.WishlistPO;
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
		fakeData = DataUtil.getData();
		
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		
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
	public void Add_To_Cart_01() {
		log.info("Wishlist_01 - Step 01: Open sub menu 'Notebooks'");
		registerPage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Notebooks ");
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Wishlist_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Apple MacBook Pro 13-inch");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("Wishlist_01 - Step 03: Click to 'Add your review' link");
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Wishlist_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Asus N551JK-XO076H Laptop");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Wishlist_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Samsung Series 9 NP900X4C Premium Ultrabook");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Wishlist_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"HP Spectre XT Pro UltraBook");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.backPage(driver);
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Wishlist_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"HP Envy 6-1180ca 15.6-Inch Sleekbook");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		productDetailsPage.openMenuFooterPageByName(driver, "Recently viewed products");
		recentlyViewPage = PageGenerator.getRecentlyViewedProductsPage(driver);
		recentlyViewPage.isProductDisplayedByTitle(driver, "Samsung Series 9 NP900X4C Premium Ultrabook");
		recentlyViewPage.isProductDisplayedByTitle(driver, "HP Spectre XT Pro UltraBook");
		recentlyViewPage.isProductDisplayedByTitle(driver, "HP Envy 6-1180ca 15.6-Inch Sleekbook");
		
		
		
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
	NotebooksPO notebooksPage;
	DesktopsPO desktopsPage;
	ProductDetailsPO productDetailsPage;
	WishlistPO wishlistPage;
	RecentlyViewrdProductsPO recentlyViewPage;
}

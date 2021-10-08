
	package com.nopcommerce.wishlist;

	import org.openqa.selenium.WebDriver;

	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;

import commons.BaseTest;
import pageObject.user.nopCommerce.ComparePO;
import pageObject.user.nopCommerce.DesktopsPO;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.NotebooksPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.ProductDetailsPO;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.WishlistPO;
import utilities.DataUtil;


	public class TC_04_Add_To_Compare extends BaseTest {
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
		public void Add_To_Compare_01() {
			log.info("Wishlist_01 - Step 01: Open sub menu 'Desktops '");
			homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
			desktopsPage = PageGenerator.getDesktopsPage(driver);
			
			log.info("Wishlist_01 - Step 02: Click to the product title link");
			desktopsPage.clickToButtonAddToSomethingByProductTitleAndButtonTitle(driver,"Build your own computer","Add to compare list");
			desktopsPage.isJQueryAjaxLoadedSuccess(driver);
			
			log.info("Wishlist_01 - Step 04: Verify message is displayed");
			verifyEquals(desktopsPage.getMessageInProductDetailsDisplayedByText(driver), "The product has been added to your product comparison");
			
			log.info("Wishlist_01 - Step 05: Click to Close icon");
			desktopsPage.clickToCloseIconInMessage(driver);
			
			log.info("Wishlist_01 - Step 02: Click to the product title link");
			desktopsPage.clickToButtonAddToSomethingByProductTitleAndButtonTitle(driver,"Digital Storm VANQUISH 3 Custom Performance PC","Add to compare list");
			desktopsPage.isJQueryAjaxLoadedSuccess(driver);
			
			log.info("Wishlist_01 - Step 04: Verify message is displayed");
			verifyEquals(desktopsPage.getMessageInProductDetailsDisplayedByText(driver), "The product has been added to your product comparison");

			log.info("Wishlist_01 - Step 05: Click to Close icon");
			desktopsPage.clickToCloseIconInMessage(driver);
			
			log.info("Pre-Condition - Step 02: Open Register 'Compare products list' page on footer");
			desktopsPage.openMenuFooterPageByName(driver, "Compare products list");
			comparePage = PageGenerator.getComparePage(driver);
			
			log.info("Wishlist_01 - Step 07: Verify Product Information displayed at table");
			verifyEquals(comparePage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "compare-products-table", "2", "remove-product"),"Remove");
			verifyEquals(comparePage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "compare-products-table", "3", "remove-product"),"Remove");
			verifyEquals(comparePage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "compare-products-table", "1", "product-name"),"Name");
			verifyEquals(comparePage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "compare-products-table", "1", "product-price"),"Price");
			verifyEquals(comparePage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "compare-products-table", "3", "product-price"),"$1,200.00");
			
			comparePage.clickToClearListButton();
			comparePage.isPageMessageNoDataDisplayedByText(driver, "You have no items to compare.");
			
			verifyTrue(comparePage.isRowProductItemDisplayedByText(" ","Remove","Remove"));
			verifyTrue(comparePage.isRowProductItemDisplayedByText("Name","Digital Storm VANQUISH 3 Custom Performance PC","Build your own computer"));
			verifyTrue(comparePage.isRowProductItemDisplayedByText("Price","$1,259.00","$1,200.00"));
			
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
		ComparePO comparePage;

	}


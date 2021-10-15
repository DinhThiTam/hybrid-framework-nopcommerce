
package com.nopcommerce.order;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;
import com.nopcommerce.data.Customers.NewAddress;

import commons.BaseTest;
import pageObject.user.nopCommerce.CheckoutPO;
import pageObject.user.nopCommerce.ComparePO;
import pageObject.user.nopCommerce.DesktopsPO;
import pageObject.user.nopCommerce.HomePO;
import pageObject.user.nopCommerce.LoginPO;
import pageObject.user.nopCommerce.MyAccountPO;
import pageObject.user.nopCommerce.NotebooksPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.ProductDetailsPO;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.ShoppingCartPO;
import pageObject.user.nopCommerce.WishlistPO;
import pageUIs.nopCommerce.ProductDetailsPageUI;
import utilities.DataUtil;

public class TC_05_Checkout_Order extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, processorProduct, ramProduct, hddProduct, sProduct,
			softwareMicrosoft, softwareAcrobat, softwareTotal, stringUnitPrice,sProduct1, processorProduct1, ramProduct1,hddProduct1, stringTotal;
	float totalPrice, floatUnitPrice, floatTotal;
	int quantity;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		fakeData = DataUtil.getData();

		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();

		processorProduct = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		processorProduct1 = "2.2 GHz Intel Pentium Dual-Core E2200";
		ramProduct = "8GB [+$60.00]";
		ramProduct1 = "4GB [+$20.00]";
		hddProduct = "400 GB [+$100.00]";
		hddProduct1 = "320 GB";
		sProduct = "Vista Premium [+$60.00]";
		sProduct1 = "Vista Home [+$50.00]";
		softwareMicrosoft = "Microsoft Office [+$50.00]";
		softwareAcrobat = "Acrobat Reader [+$10.00]";
		softwareTotal = "Total Commander [+$5.00]";
		
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
	public void TC_04_Update_Shopping_Cart() {
		log.info("Wishlist_01 - Step 01: Open sub menu 'Notebooks'");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Notebooks ");
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("Wishlist_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Apple MacBook Pro 13-inch");
		notebooksPage.sleepInsecond(3);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage.clickToButtonByClassAndName(driver, "add-to-cart", "Add to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage.openMenuFooterPageByName(driver, "Shopping cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
	
		
		shoppingCartPage.clickToButtonByClassAndName(driver, "common-buttons","Estimate shipping");
		shoppingCartPage.sleepInsecond(5);
		shoppingCartPage.selectItemInDropdownByName(driver, NewAddress.COUNTRY_NAME, "CountryId");
		shoppingCartPage.sleepInsecond(5);
		shoppingCartPage.getSelectItemInDropdownByName(driver, "CountryId");
		shoppingCartPage.getSelectItemInDropdownByName(driver, "StateProvinceId");
		shoppingCartPage.enterToTextboxByID(driver, "ZipPostalCode", NewAddress.ZIP_CODE);
		shoppingCartPage.isJQueryAjaxLoadedSuccess(driver);
		shoppingCartPage.clickToRadioByText(driver, "Next Day Air");
		shoppingCartPage.clickToButtonByName(driver,"Apply");
		verifyEquals(shoppingCartPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "earn-reward-points"), "360 points");
		shoppingCartPage.clickToRadioAndCheckboxByLabel(driver, "I agree with the terms of service and I adhere to them unconditionally");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);
		checkoutPage.uncheckToCheckboxByLabel(driver, "Ship to the same address");
		checkoutPage.selectItemInDropdownByName(driver, NewAddress.COUNTRY_NAME, "BillingNewAddress.CountryId");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		verifyEquals(checkoutPage.getSelectItemInDropdownByName(driver,"BillingNewAddress.StateProvinceId"),"Other");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_City", NewAddress.CITY_NAME);
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address1", NewAddress.ADDRESS1);
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", NewAddress.ZIP_CODE);
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", NewAddress.PHONE_NUMBER);
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Billing address", "Continue");
		
	
		
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping address", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Next Day Air ($0.00)");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping method", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Check / Money Order");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment method", "Continue");
	}
	
	


	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
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
	ShoppingCartPO shoppingCartPage;
	CheckoutPO checkoutPage;

}

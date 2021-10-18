
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
import pageObject.user.nopCommerce.OrderDetailsPO;
import pageObject.user.nopCommerce.PageGenerator;
import pageObject.user.nopCommerce.ProductDetailsPO;
import pageObject.user.nopCommerce.RegisterPO;
import pageObject.user.nopCommerce.ShoppingCartPO;
import pageObject.user.nopCommerce.WishlistPO;
import utilities.DataUtil;

public class TC_06_Checkout_Order_Payment_Method_Cheque extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, cardNumber, cardHolderName;
	int cardCode;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		fakeData = DataUtil.getData();

		cardNumber = fakeData.getCardNumber();
		cardHolderName = fakeData.getCardHolderName();
		cardCode = fakeData.getCode();
		
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
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Credit Card");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment method", "Continue");
		checkoutPage.selectItemInDropdownByName(driver, "Visa", "CreditCardType");
		checkoutPage.enterToTextboxByID(driver, "CardholderName", cardHolderName);
		checkoutPage.enterToTextboxByID(driver, "CardNumber", cardNumber);
		checkoutPage.selectItemInDropdownByName(driver, "12", "ExpireMonth");
		checkoutPage.selectItemInDropdownByName(driver, "2035", "ExpireYear");
		checkoutPage.enterToTextboxByID(driver, "CardCode", String.valueOf(cardCode) );
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment information", "Continue");
		
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver, "Billing Address","name"),NewAddress.FULL_NAME);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","fax"),"Fax:");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","country"),NewAddress.COUNTRY_NAME);
		
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Payment","payment-method"),"Payment Method: Credit Card");
		
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","name"),NewAddress.FULL_NAME);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","fax"),"Fax:");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","country"),NewAddress.COUNTRY_NAME);
		
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping","shipping-method"),"Shipping Method: Next Day Air");
		
		
		verifyTrue(checkoutPage.isValueInTableDisplayed(driver,"AP_MBP_13", "Apple MacBook Pro 13-inch","$1,800.00", "2","$3,600.00"));
		
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "order-subtotal"), "$3,600.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "shipping-cost"), "$0.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "tax-value"), "$0.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "order-total"), "$3,600.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "earn-reward-points"), "360 points");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Confirm order", "Confirm");
		
		verifyTrue(checkoutPage.isMessageSuccessOrderDisplayed());
		String orderNumber = checkoutPage.getOrderNumber(driver, "order-number");
		verifyEquals(checkoutPage.getFullOrderNumber(driver,"order-number"),"ORDER NUMBER: " + orderNumber);
		
		log.info("Add_Address_01 - Step 02: Open 'Orders' form");
		checkoutPage.openMenuFooterPageByName(driver, "Orders");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		
		
		myAccountPage.sleepInsecond(2);
		
		verifyEquals(myAccountPage.getFullOrderNumber(driver,"order-list"),"Order Number: " + orderNumber);
		System.out.println("NUmber order 1: " + orderNumber);
		myAccountPage.clickToButtonByName(driver, "Details");
		myAccountPage.isJQueryAjaxLoadedSuccess(driver);
		orderDetailsPage = PageGenerator.getOrderDetailsPage(driver);
		orderDetailsPage.sleepInsecond(3);
		verifyEquals(orderDetailsPage.getFullOrderNumber(driver,"order-number"),"ORDER #"+orderNumber);
		orderDetailsPage.sleepInsecond(3);
		
		verifyEquals(orderDetailsPage.getFullOrderDateAndOrderStatus(driver,"order-date"),"Order Date: " + orderDetailsPage.getSubStringInOrderDateAndOrderStatus(driver, "order-date"));
		verifyEquals(orderDetailsPage.getFullOrderDateAndOrderStatus(driver,"order-status"),"Order Status: Pending");
		verifyEquals(orderDetailsPage.getFullOrderDateAndOrderStatus(driver,"order-total"),"Order Total: $3,600.00");
		
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver, "Billing Address","name"),NewAddress.FULL_NAME);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","fax"),"Fax:");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","country"),NewAddress.COUNTRY_NAME);
		
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Payment","payment-method"),"Payment Method: Credit Card");
		
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","name"),NewAddress.FULL_NAME);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","fax"),"Fax:");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","country"),NewAddress.COUNTRY_NAME);
		
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping","shipping-method"),"Shipping Method: Next Day Air");
		
		
		verifyTrue(orderDetailsPage.isValueInTableDisplayed(driver,"AP_MBP_13", "Apple MacBook Pro 13-inch","$1,800.00", "2","$3,600.00"));
		
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
	OrderDetailsPO orderDetailsPage;

}

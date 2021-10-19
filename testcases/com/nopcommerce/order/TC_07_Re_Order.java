
package com.nopcommerce.order;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;
import com.nopcommerce.data.Customers.EditAddress;
import com.nopcommerce.data.Customers.NewAddress;
import com.nopcommerce.data.Customers.UpdateAddress;

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

public class TC_07_Re_Order extends BaseTest {
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
		emailAddress = fakeData.getEditEmailAddress();
		
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
	public void TC_01_Re_Order() {
		log.info("TC_01 - Step 01: Open sub menu 'Notebooks'");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Notebooks ");
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("TC_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Apple MacBook Pro 13-inch");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("TC_01 - Step 03:Click to 'Add to cart' button");
		productDetailsPage.clickToButtonByClassAndName(driver, "add-to-cart", "Add to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 04: Open 'Shopping cart' menu");
		productDetailsPage.openMenuFooterPageByName(driver, "Shopping cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
	
		log.info("TC_01 - Step 05:Click to 'Estimate shipping' button");
		shoppingCartPage.clickToButtonByClassAndName(driver, "common-buttons","Estimate shipping");
		shoppingCartPage.sleepInsecond(5);
		
		log.info("TC_01 - Step 06:Select to 'Country name' in dropdown");
		shoppingCartPage.selectItemInDropdownByName(driver, NewAddress.COUNTRY_NAME, "CountryId");
		shoppingCartPage.sleepInsecond(5);
		
		log.info("TC_01 - Step 07: Verify item in dropdown is displayed");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByName(driver, "CountryId"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 08: Verify item in dropdown is displayed");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByName(driver, "StateProvinceId"),NewAddress.STATE_PROVINCE);
		
		log.info("TC_01 - Step 09:Enter to 'Zip Code' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "ZipPostalCode", NewAddress.ZIP_CODE);
		shoppingCartPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 10:Click to 'Next Day Air' radio button");
		shoppingCartPage.clickToRadioByText(driver, "Next Day Air");
		
		log.info("TC_01 - Step 11:Click to 'Apply' radio button");
		shoppingCartPage.clickToButtonByName(driver,"Apply");
		
		log.info("TC_01 - Step 12:Verify the product is displayed in the table");
		verifyEquals(shoppingCartPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "earn-reward-points"), "360 points");
		
		log.info("TC_01 - Step 13:Click to checkbox");
		shoppingCartPage.clickToRadioAndCheckboxByLabel(driver, "I agree with the terms of service and I adhere to them unconditionally");
		
		log.info("TC_01 - Step 14:Click to 'Check out' button");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);
		
		log.info("TC_01 - Step 15:Uncheck to 'Ship to the same address' checkbox");
		checkoutPage.uncheckToCheckboxByLabel(driver, "Ship to the same address");
		
		log.info("TC_01 - Step 16:Select to 'Country name' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, NewAddress.COUNTRY_NAME, "BillingNewAddress.CountryId");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 17: Verify item in dropdown is displayed");
		verifyEquals(checkoutPage.getSelectItemInDropdownByName(driver,"BillingNewAddress.StateProvinceId"),"Other");
		
		log.info("TC_01 - Step 18:Enter to 'City' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_City", NewAddress.CITY_NAME);
		
		log.info("TC_01 - Step 19:Enter to 'Address1' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address1", NewAddress.ADDRESS1);
		
		log.info("TC_01 - Step 20:Enter to 'Zip code' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", NewAddress.ZIP_CODE);
		
		log.info("TC_01 - Step 21:Enter to 'Phone number' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", NewAddress.PHONE_NUMBER);
		
		log.info("TC_01 - Step 22:Click to 'Continue' button' in 'Billing address' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Billing address", "Continue");
		
		log.info("TC_01 - Step 23:Click to 'Continue' button' in 'Shipping addresss' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping address", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 24:Click to 'Next Day Air ($0.00' radio");
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Next Day Air ($0.00)");
		
		log.info("TC_01 - Step 25:Click to 'Continue' button' in 'Shipping method' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping method", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 26:Click to 'Credit Card' radio");
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Credit Card");
		
		log.info("TC_01 - Step 27:Click to 'Continue' button' in 'Payment method' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment method", "Continue");
		
		log.info("TC_01 - Step 28:Select to 'Visa' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "Visa", "CreditCardType");

		log.info("TC_01 - Step 29:Enter to 'CardholderName' textbox");
		checkoutPage.enterToTextboxByID(driver, "CardholderName", cardHolderName);
		
		log.info("TC_01 - Step 30:Enter to 'CardNumber' textbox");
		checkoutPage.enterToTextboxByID(driver, "CardNumber", cardNumber);
		
		log.info("TC_01 - Step 31:Select to 'ExpireMonth' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "12", "ExpireMonth");
		
		log.info("TC_01 - Step 32:Select to 'ExpireYear' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "2035", "ExpireYear");
		
		log.info("TC_01 - Step 33:Enter to 'CardCode' textbox");
		checkoutPage.enterToTextboxByID(driver, "CardCode", String.valueOf(cardCode) );
		
		log.info("TC_01 - Step 34:Click to 'Continue' button' in 'Payment information' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment information", "Continue");
		
		log.info("TC_01 - Step 35:Verify the infomation in 'Billing Address' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver, "Billing Address","name"),NewAddress.FULL_NAME);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","fax"),"Fax:");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","country"),NewAddress.COUNTRY_NAME);
		
		
		log.info("TC_01 - Step 36:Verify the infomation in 'Payment Method' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Payment","payment-method"),"Payment Method: Credit Card");
		
		log.info("TC_01 - Step 37:Verify the infomation in 'Shipping Address' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","name"),NewAddress.FULL_NAME);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","fax"),"Fax:");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","country"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 38:Verify the infomation in 'Shipping Method' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping","shipping-method"),"Shipping Method: Next Day Air");
		
		log.info("TC_01 - Step 39:Verify the product in the table is displayed");
		verifyTrue(checkoutPage.isValueInTableDisplayed(driver,"AP_MBP_13", "Apple MacBook Pro 13-inch","$1,800.00", "2","$3,600.00"));
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "order-subtotal"), "$3,600.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "shipping-cost"), "$0.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "tax-value"), "$0.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "order-total"), "$3,600.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "earn-reward-points"), "360 points");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Confirm order", "Confirm");
		
		log.info("TC_01 - Step 40:Verify Order Number is displayed ");
		String orderNumber = checkoutPage.getOrderNumber(driver, "order-number");
		verifyEquals(checkoutPage.getFullOrderNumber(driver,"order-number"),"ORDER NUMBER: " + orderNumber);
		
		log.info("TC_01 - Step 41 - Step 02: Open 'Orders' form");
		log.info("TC_01 - Step 42 - Step 02: Open 'Orders' form");
		checkoutPage.openMenuFooterPageByName(driver, "Orders");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		myAccountPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 43:Verify Order list  value is displayed with order number value ");
		verifyEquals(myAccountPage.getFullOrderNumber(driver,"order-list"),"Order Number: " + orderNumber);
		System.out.println("NUmber order 1: " + orderNumber);
		
		log.info("TC_01 - Step 44:Click to 'Details' button");
		myAccountPage.clickToButtonByName(driver, "Details");
		myAccountPage.isJQueryAjaxLoadedSuccess(driver);
		orderDetailsPage = PageGenerator.getOrderDetailsPage(driver);
		orderDetailsPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 45:Verify text Order Number is displayed ");
		verifyEquals(orderDetailsPage.getFullOrderNumber(driver,"order-number"),"ORDER #"+orderNumber);
		
		log.info("TC_01 - Step 46:Click to 'Re-order' button");
		orderDetailsPage.clickToButtonByName(driver, "Re-order");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		
		log.info("TC_01 - Step 47:Enter to quantity =  10");
		shoppingCartPage.enterToInputQuantityTextbox("10");
		shoppingCartPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 48:Click to 'Update shopping cart' button");
		shoppingCartPage.clickToButtonByName(driver, "Update shopping cart");
		shoppingCartPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 49:Click to checkbox");
		shoppingCartPage.clickToRadioAndCheckboxByLabel(driver, "I agree with the terms of service and I adhere to them unconditionally");
		
		log.info("TC_01 - Step 50:Click to 'Check out' button");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);
		
		log.info("TC_01 - Step 51:Uncheck to 'Ship to the same address' checkbox");
		checkoutPage.uncheckToCheckboxByLabel(driver, "Ship to the same address");
		
		log.info("TC_01 - Step 52:Select to 'New Address' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "New Address", "billing_address_id");
		
		log.info("TC_01 - Step 53:Enter to 'First name' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_FirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_01 - Step 54:Enter to 'Last name' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_LastName", EditAddress.EDIT_LASTNAME);
		
		log.info("TC_01 - Step 55:Enter to 'Email' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Email",emailAddress );
		
		log.info("TC_01 - Step 56:Select to 'Country name' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, UpdateAddress.UPDATE_COUNTRY_NAME, "BillingNewAddress.CountryId");
		
		log.info("TC_01 - Step 57:Select to 'State Province' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "Alaska", "BillingNewAddress.StateProvinceId");
	
		log.info("TC_01 - Step 58:Enter to 'City' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_City", UpdateAddress.UPDATE_CITY_NAME);
		
		log.info("TC_01 - Step 59:Enter to 'Address1' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address1", UpdateAddress.UPDATE_ADDRESS1);
		
		log.info("TC_01 - Step 60:Enter to 'Zip code' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", UpdateAddress.UPDATE_ZIP_CODE);
		
		log.info("TC_01 - Step 61:Enter to 'Phone number' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", UpdateAddress.UPDATE_PHONE_NUMBER);
		
		log.info("TC_01 - Step 62:Click to 'Continue' button' in 'Billing address' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Billing address", "Continue");
		
		log.info("TC_01 - Step 63:Click to 'Continue' button' in 'Shipping addresss' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping address", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 64:Click to 'Next Day Air ($0.00' radio");
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Next Day Air ($0.00)");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping method", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 65:Click to 'Credit Card' radio");
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Credit Card");
		
		log.info("TC_01 - Step 66:Click to 'Continue' button' in 'Payment method' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment method", "Continue");
		
		log.info("TC_01 - Step 67:Select to 'Visa' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "Visa", "CreditCardType");
		
		log.info("TC_01 - Step 68:Enter to 'CardholderName' textbox");
		checkoutPage.enterToTextboxByID(driver, "CardholderName", cardHolderName);
		
		log.info("TC_01 - Step 69:Enter to 'CardNumber' textbox");
		checkoutPage.enterToTextboxByID(driver, "CardNumber", cardNumber);
		
		log.info("TC_01 - Step 70:Select to 'ExpireMonth' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "12", "ExpireMonth");
		
		log.info("TC_01 - Step 71:Select to 'ExpireYear' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, "2035", "ExpireYear");
		
		log.info("TC_01 - Step 72:Enter to 'CardCode' textbox");
		checkoutPage.enterToTextboxByID(driver, "CardCode", String.valueOf(cardCode) );
		
		log.info("TC_01 - Step 73:Click to 'Continue' button' in 'Payment information' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment information", "Continue");
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

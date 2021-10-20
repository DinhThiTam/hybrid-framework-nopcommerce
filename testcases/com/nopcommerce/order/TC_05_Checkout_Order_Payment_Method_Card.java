
package com.nopcommerce.order;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login_User;
import com.nopcommerce.data.Customers.NewAddress;
import com.nopcommerce.data.Payment.PaymentInfo;

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

public class TC_05_Checkout_Order_Payment_Method_Card extends BaseTest {
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
	public void TC_01_Checkout_Order_Method_Card() {
		log.info("TC_01 - Step 01: Open sub menu 'Notebooks'");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Notebooks ");
		notebooksPage = PageGenerator.getNotebooksPage(driver);
		
		log.info("TC_01 - Step 02: Click to the product title link");
		notebooksPage.clickToProductLinkByText(driver,"Apple MacBook Pro 13-inch");
		notebooksPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
	
		log.info("TC_01 - Step 03:Click to 'Add to cart' button");
		productDetailsPage.clickToButtonByClassAndName(driver, "add-to-cart", "Add to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 04: Open 'Shopping cart' menu");
		productDetailsPage.openMenuFooterPageByName(driver, "Shopping cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		
		log.info("TC_01 - Step 04: Remove the product in the table");
		shoppingCartPage.clickToRemoveIconInTableByRowValue("LE_IC_600", "Lenovo IdeaCentre 600 All-in-One PC", "$500.00", "5", "$2,500.00");
		shoppingCartPage.isJQueryAjaxLoadedSuccess(driver);
	
		log.info("TC_01 - Step 05:Click to 'Estimate shipping' button");
		shoppingCartPage.clickToButtonByClassAndName(driver, "common-buttons","Estimate shipping");
		shoppingCartPage.sleepInsecond(5);
		
		log.info("TC_01 - Step 06:Select to 'Country name' in dropdown");
		shoppingCartPage.selectItemInDropdownByName(driver, NewAddress.COUNTRY_NAME, "CountryId");
		shoppingCartPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 06: Select item 'State Province' in dropdown");
		shoppingCartPage.selectItemInDropdownByNameAtAdminSite(driver, NewAddress.STATE_PROVINCE, "StateProvinceId");
		
		log.info("TC_01 - Step 06: Verify item in dropdown is displayed");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByName(driver, "CountryId"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 07: Verify item in dropdown is displayed");
		verifyEquals(shoppingCartPage.getSelectItemInDropdownByName(driver, "StateProvinceId"),NewAddress.STATE_PROVINCE);
		
		log.info("TC_01 - Step 08:Enter to 'Zip Code' textbox");
		shoppingCartPage.enterToTextboxByID(driver, "ZipPostalCode", NewAddress.ZIP_CODE);
		shoppingCartPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 09:Click to 'Next Day Air' radio button");
		shoppingCartPage.clickToRadioByText(driver, "Next Day Air");
		
		log.info("TC_01 - Step 10:Click to 'Apply' radio button");
		shoppingCartPage.clickToButtonByName(driver,"Apply");
		
		log.info("TC_01 - Step 11:Verify the product is displayed in the table");
		verifyEquals(shoppingCartPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "earn-reward-points"), "360 points");
		
		log.info("TC_01 - Step 12:Click to checkbox");
		shoppingCartPage.clickToRadioAndCheckboxByID(driver, "termsofservice");
		
		log.info("TC_01 - Step 13:Click to 'Check out' button");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);
		
		log.info("TC_01 - Step 14:Uncheck to 'Ship to the same address' checkbox");
		checkoutPage.uncheckToCheckboxByLabel(driver, "Ship to the same address");
		
		log.info("TC_01 - Step 15:Select to 'Country name' in dropdown");
		checkoutPage.selectItemInDropdownByName(driver, NewAddress.COUNTRY_NAME, "BillingNewAddress.CountryId");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 16: Verify item in dropdown is displayed");
		verifyEquals(checkoutPage.getSelectItemInDropdownByName(driver,"BillingNewAddress.StateProvinceId"),"Other");
		
		log.info("TC_01 - Step 17:Enter to 'City' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_City", NewAddress.CITY_NAME);
		
		log.info("TC_01 - Step 17:Enter to 'Address1' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address1", NewAddress.ADDRESS1);
		
		log.info("TC_01 - Step 19:Enter to 'Zip code' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", NewAddress.ZIP_CODE);
		
		log.info("TC_01 - Step 20:Enter to 'Phone number' textbox");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", NewAddress.PHONE_NUMBER);
		
		log.info("TC_01 - Step 03:Click to 'Continue' button' in 'Billing address' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Billing address", "Continue");
		
		log.info("TC_01 - Step 21:Click to 'Continue' button' in 'Shipping addresss' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping address", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 22:Click to 'Next Day Air ($0.00' radio");
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Next Day Air ($0.00)");
		
		log.info("TC_01 - Step 23:Click to 'Continue' button' in 'Shipping method' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Shipping method", "Continue");
		checkoutPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 24:Click to 'Credit Card' radio");
		checkoutPage.clickToRadioAndCheckboxByLabel(driver, "Check / Money Order");
		
		log.info("TC_01 - Step 25:Click to 'Continue' button' in 'Payment method' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment method", "Continue");
		
		log.info("TC_01 - Step 26:Get text payment information");
		verifyEquals(checkoutPage.getTextPayment("1"),PaymentInfo.TEXT_INDEX_1);
		verifyEquals(checkoutPage.getTextPayment("2"),PaymentInfo.TEXT_INDEX_2);
		verifyEquals(checkoutPage.getTextPayment("3"),PaymentInfo.TEXT_INDEX_3);
		verifyEquals(checkoutPage.getTextPayment("4"),PaymentInfo.TEXT_INDEX_4);
		
		log.info("TC_01 - Step 27:Click to 'Continue' button' in 'Payment information' title");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Payment information", "Continue");
		
		log.info("TC_01 - Step 28:Verify the infomation in 'Billing Address' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver, "Billing Address","name"),NewAddress.FULL_NAME);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","fax"),"Fax:");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Billing Address","country"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 29:Verify the infomation in 'Payment Method' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Payment","payment-method"),"Payment Method: Check / Money Order");
		
		log.info("TC_01 - Step30:Verify the infomation in 'Shipping Address' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","name"),NewAddress.FULL_NAME);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","fax"),"Fax:");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping Address","country"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 31:Verify the infomation in 'Shipping Method' are displayed");
		verifyEquals(checkoutPage.getInfoListByTitleAndClass(driver,"Shipping","shipping-method"),"Shipping Method: Next Day Air");
		
		log.info("TC_01 - Step 32:Verify the product in the table is displayed");
		verifyTrue(checkoutPage.isValueInTableDisplayed(driver,"AP_MBP_13", "Apple MacBook Pro 13-inch","$1,800.00", "2","$3,600.00"));
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "order-subtotal"), "$3,600.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "shipping-cost"), "$0.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "tax-value"), "$0.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "order-total"), "$3,600.00");
		verifyEquals(checkoutPage.getValueInTableIDAtColumnVerticalByClassAndRowIndex(driver, "cart-total", "2", "earn-reward-points"), "360 points");
	
		log.info("TC_01 - Step 33:Click to 'Confirm' button");
		checkoutPage.clickToButtonInCheckoutPageByTitleAndName("Confirm order", "Confirm");
		
		log.info("TC_01 - Step 34:Verify success message is displayed");
		verifyTrue(checkoutPage.isMessageSuccessOrderDisplayed());
		
		log.info("TC_01 - Step 35:Verify order number value");
		String orderNumber = checkoutPage.getOrderNumber(driver, "order-number");
		verifyEquals(checkoutPage.getFullOrderNumber(driver,"order-number"),"ORDER NUMBER: " + orderNumber);
		
		log.info("TC_01 - Step 36: Open 'Orders' form");
		checkoutPage.openMenuFooterPageByName(driver, "Orders");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		myAccountPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 37:Verify order number value with order list value");
		verifyEquals(myAccountPage.getFullOrderNumber(driver,"order-list"),"Order Number: " + orderNumber);

		log.info("TC_01 - Step 38:Click to 'Details' button");
		myAccountPage.clickToButtonByName(driver, "Details");
		myAccountPage.isJQueryAjaxLoadedSuccess(driver);
		orderDetailsPage = PageGenerator.getOrderDetailsPage(driver);
		
		log.info("TC_01 - Step 39:Verify text order number is displayed");
		verifyEquals(orderDetailsPage.getFullOrderNumber(driver,"order-number"),"ORDER #"+orderNumber);
		orderDetailsPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 40:Verify the infomation in 'Order Date', 'Order Status', 'Order Total' are displayed");
		verifyEquals(orderDetailsPage.getFullOrderDateAndOrderStatus(driver,"order-date"),"Order Date: " + orderDetailsPage.getSubStringInOrderDateAndOrderStatus(driver, "order-date"));
		verifyEquals(orderDetailsPage.getFullOrderDateAndOrderStatus(driver,"order-status"),"Order Status: Pending");
		verifyEquals(orderDetailsPage.getFullOrderDateAndOrderStatus(driver,"order-total"),"Order Total: $3,600.00");
		
		log.info("TC_01 - Step 41:Verify the infomation in 'Billing Address' are displayed");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver, "Billing Address","name"),NewAddress.FULL_NAME);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","fax"),"Fax:");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Billing Address","country"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 42:Verify the infomation in 'Payment Method' are displayed");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Payment","payment-method"),"Payment Method: Check / Money Order");
		
		log.info("TC_01 - Step 43:Verify the infomation in 'Shipping Address' are displayed");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","name"),NewAddress.FULL_NAME);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","email"),"Email: " + Common_01_Login_User.emailAddress);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","phone"),"Phone: " + NewAddress.PHONE_NUMBER);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","fax"),"Fax:");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","address1"),NewAddress.ADDRESS1);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","city-state-zip"),NewAddress.CITY_ZIP_CODE);
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping Address","country"),NewAddress.COUNTRY_NAME);
		
		log.info("TC_01 - Step 44:Verify the infomation in 'Shipping Method' are displayed");
		verifyEquals(orderDetailsPage.getInfoListByTitleAndClass(driver,"Shipping","shipping-method"),"Shipping Method: Next Day Air");
		
		log.info("TC_01 - Step 45:Verify the product in the table is displayed");
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

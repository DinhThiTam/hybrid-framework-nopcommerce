
package com.nopcommerce.order;


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
import pageObject.user.nopCommerce.ShoppingCartPO;
import pageObject.user.nopCommerce.WishlistPO;
import utilities.DataUtil;

public class TC_01_Add_Product_To_Cart extends BaseTest {
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
	public void TC_01_Add_Product_To_Cart() {
		log.info("TC_01 - Step 01: Open sub menu 'Desktops'");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
		desktopsPage = PageGenerator.getDesktopsPage(driver);

		log.info("TC_01 - Step 02: Click to Product Link");
		desktopsPage.clickToProductLinkByText(driver, "Build your own computer");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);

		log.info("TC_01 - Step 03: Click to 'Processor' dropdown");
		productDetailsPage.selectItemInDropdownByName(driver, processorProduct,"product_attribute_1");
		
		log.info("TC_01 - Step 04: Verify item in dropdown is selected");
		verifyEquals(productDetailsPage.getSelectItemInDropdownByName(driver, "product_attribute_1"),processorProduct);
		
		log.info("TC_01 - Step 05: Click to 'RAM' dropdown");
		productDetailsPage.selectItemInDropdownByName(driver, ramProduct, "product_attribute_2");
		
		log.info("TC_01 - Step 06: Verify item in dropdown is selected");
		verifyEquals(productDetailsPage.getSelectItemInDropdownByName(driver, "product_attribute_2"),ramProduct);
		
		log.info("TC_01 - Step 07: Click to 'HDD' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, hddProduct);
		
		log.info("TC_01 - Step 08: Click to 'OS' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, sProduct);
		
		log.info("TC_01 - Step 09: Click to 'Microsoft Office [+$50.00]' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, softwareMicrosoft);
		
		log.info("TC_01 - Step 10: Click to 'Acrobat Reader [+$10.00]' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, softwareAcrobat);
		
		log.info("TC_01 - Step 11: Click to 'Total Commander [+$5.00]' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, softwareTotal);
		
		log.info("TC_01 - Step 12: Click to 'Add to cart' button");
		productDetailsPage.clickToButtonByName(driver, "Add to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_01 - Step 13: Verify message is displayed");
		verifyEquals(productDetailsPage.getMessageInProductDetailsDisplayedByText(driver),"The product has been added to your shopping cart");

		log.info("TC_01 - Step 14: Click to Close icon");
		productDetailsPage.clickToCloseIconInMessage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_01 - Step 15: Hover to mini shopping cart on header menu");
		productDetailsPage.hoverToShoppingCartHeaderMenu();
		
		log.info("TC_01 - Step 16: Verify the infomation of the product displayed in the cart");
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("There are 1 item(s) in your cart."));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass(" Processor: " + processorProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("RAM: " + ramProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("HDD: " + hddProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("OS: " + sProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Software: " + softwareMicrosoft));
		
		quantity = 1;
		
		log.info("TC_01 - Step 17: Get the value of the price unit and parse as float");
		floatUnitPrice= productDetailsPage.getPriceUnit("price");
		
		log.info("TC_01 - Step 17: Get text of element");
		stringUnitPrice = productDetailsPage.getTextPriceUnit("price");
		
		log.info("TC_01 - Step 16: Verify unit price of the product displayed in the cart");
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass(stringUnitPrice));
	
		log.info("TC_01 - Step 16: Verify quantity of the product displayed in the cart");
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Quantity: " + String.valueOf(quantity)));
	
		log.info("TC_01 - Step 16: Verify total price of the product displayed in the cart");
		totalPrice =quantity*floatUnitPrice;
		verifyEquals(productDetailsPage.getPriceUnit("totals"),totalPrice);
	}
	
	@Test
	public void TC_02_Edit_Product_To_Cart() {
		log.info("TC_02 - Step 16: Click to 'Go to cart' button");
		productDetailsPage.clickToButtonByName(driver, "Go to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		
		log.info("TC_02 - Step 16: Click to 'Edit' button in table");
		productDetailsPage = shoppingCartPage.clickToEditButtonInTableByRowNumber("1");
		productDetailsPage.sleepInsecond(5);
		
		log.info("TC_01 - Step 03: Click to 'Processor' dropdown");
		productDetailsPage.selectItemInDropdownByName(driver, processorProduct1,"product_attribute_1");
		
		log.info("TC_01 - Step 04: Verify item in dropdown is selected");
		verifyEquals(productDetailsPage.getSelectItemInDropdownByName(driver, "product_attribute_1"),processorProduct1);
		
		log.info("TC_01 - Step 03: Click to 'RAM' dropdown");
		productDetailsPage.selectItemInDropdownByName(driver, ramProduct1, "product_attribute_2");
		
		log.info("TC_01 - Step 04: Verify item in dropdown is selected");
		verifyEquals(productDetailsPage.getSelectItemInDropdownByName(driver, "product_attribute_2"),ramProduct1);
		
		log.info("TC_01 - Step 07: Click to 'HDD' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, hddProduct1);
		
		log.info("TC_01 - Step 07: Click to 'OS' radio button");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, sProduct1);
		productDetailsPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 07: Uncheck to 'Acrobat Reader [+$10.00]' checkbox");
		productDetailsPage.uncheckToCheckboxByLabel(driver, softwareAcrobat);
		productDetailsPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 07: Uncheck to 'Total Commander [+$5.00]' checkbox");
		productDetailsPage.uncheckToCheckboxByLabel(driver, softwareTotal);
		productDetailsPage.sleepInsecond(3);
		
		quantity = 2;
		
		log.info("TC_01 - Step 07: Enter to quantity textbox");
		productDetailsPage.enterToTextboxByID(driver, "product_enteredQuantity_1", String.valueOf(quantity));
		
		log.info("TC_01 - Step 07: Verify the price is '$1,320.00'");
		verifyEquals(productDetailsPage.getPriceAtProductDetailPage(),"$1,320.00");
		
		log.info("TC_01 - Step 07:Click to 'Update' button");
		productDetailsPage.clickToButtonByName(driver, "Update");
		
		log.info("Wishlist_01 - Step 04: Verify message is displayed");
		verifyEquals(productDetailsPage.getMessageInProductDetailsDisplayedByText(driver),"The product has been added to your shopping cart");
		
		log.info("Wishlist_01 - Step 05: Click to Close icon");
		productDetailsPage.clickToCloseIconInMessage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Wishlist_01 - Step 05: Hover to mini shopping cart");
		productDetailsPage.hoverToShoppingCartHeaderMenu();
		
		log.info("Wishlist_01 - Step 04: Verify the infomation of the product displayed in the cart");
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("There are 2 item(s) in your cart."));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass(" Processor: " + processorProduct1));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("RAM: " + ramProduct1));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("HDD: " + hddProduct1));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("OS: " + sProduct1));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Software: " + softwareMicrosoft));
		
		log.info("TC_01 - Step 17: Get the value of the price unit and parse as float");
		floatUnitPrice= productDetailsPage.getPriceUnit("price");
		
		log.info("TC_01 - Step 17: Get text of element");
		stringUnitPrice = productDetailsPage.getTextPriceUnit("price");
		
		log.info("TC_01 - Step 16: Verify unit price of the product displayed in the cart");
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass(stringUnitPrice));
		
		log.info("TC_01 - Step 16: Verify quantity of the product displayed in the cart");
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Quantity: " + String.valueOf(quantity)));
		
		log.info("TC_01 - Step 16: Verify total price of the product displayed in the cart");
		totalPrice =quantity*floatUnitPrice;
		verifyEquals(productDetailsPage.getPriceUnit("totals"),totalPrice);
		
	}
	@Test
	public void TC_03_Remove_from_Cart() {
		log.info("TC_02 - Step 16: Click to 'Go to cart' button");
		productDetailsPage.clickToButtonByName(driver, "Go to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		
		log.info("TC_01 - Step 16: Click to 'Remove' button in table");
		shoppingCartPage.clickToRemoveIconInTableByRowValue("COMP_CUST", "Build your own computer","$1,320.00", "2", "$2,640.00");
		
		log.info("TC_01 - Step 16: Verify message is displayed ");
		verifyTrue(shoppingCartPage.isCartEmptyMessageDisplayed());
		
		log.info("TC_01 - Step 16: Verify the product in table undisplayed ");
		verifyTrue(shoppingCartPage.isValueInTableUnDisplayed("COMP_CUST", "Build your own computer","$1,320.00", "2", "$2,640.00"));
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

}

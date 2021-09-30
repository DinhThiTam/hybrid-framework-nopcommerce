
package com.nopcommerce.order;

import org.apache.commons.collections4.sequence.ReplacementsFinder;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.nopCommerce.ComparePO;
import pageObject.nopCommerce.DesktopsPO;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.LoginPO;
import pageObject.nopCommerce.MyAccountPO;
import pageObject.nopCommerce.NotebooksPO;
import pageObject.nopCommerce.PageGenerator;
import pageObject.nopCommerce.ProductDetailsPO;
import pageObject.nopCommerce.RegisterPO;
import pageObject.nopCommerce.ShoppingCartPO;
import pageObject.nopCommerce.WishlistPO;
import utilities.DataUtil;

public class TC_01_Add_Product_To_Cart extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, processorProduct, ramProduct, hddProduct, sProduct,
			softwareMicrosoft, softwareAcrobat, softwareTotal, rightUnitPrice ;
	int quantity;
	float totalPrice, unitPrice, replaceUnitPrice;

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
		ramProduct = "8GB [+$60.00]";
		hddProduct = "400 GB [+$100.00]";
		sProduct = "Vista Premium [+$60.00]";
		softwareMicrosoft = "Microsoft Office [+$50.00]";
		softwareAcrobat = "Acrobat Reader [+$10.00]";
		softwareTotal = "Total Commander [+$5.00]";
		
		
		quantity = 1;
		
		totalPrice = unitPrice*quantity;
		log.info("Pre-Condition - Step 02: Open Register page on header");
		homePage.openMenuHeaderPageByClass(driver, "ico-register");
		registerPage = PageGenerator.getRegisterPage(driver);

		log.info("Pre-Condition - Step 03: Enter valid info to 'First Name' textbox");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		log.info("Pre-Condition - Step 04: Enter valid info to 'Last Name' textbox");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		log.info("Pre-Condition - Step 05: Enter valid info to 'Email' textbox");
		registerPage.enterToTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-Condition - Step 06: Enter valid info to 'Password' textbox");
		registerPage.enterToTextboxByID(driver, "Password", password);

		log.info("Pre-Condition - Step 07: Enter valid info to 'Confirm Password' textbox");
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Pre-Condition - Step 08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("Pre-Condition - Step 09: Verify success messages is displayed in mandantory fields");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

	}

	@Test
	public void Add_Product_To_Cart_01() {
		log.info("Wishlist_01 - Step 01: Open sub menu 'Desktops '");
		registerPage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
		desktopsPage = PageGenerator.getDesktopsPage(driver);

		desktopsPage.clickToProductLinkByText(driver, "Build your own computer");
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);

		productDetailsPage.selectItemInDropdownByName(driver, processorProduct,"product_attribute_1");
		productDetailsPage.selectItemInDropdownByName(driver, ramProduct, "product_attribute_2");
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, hddProduct);
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, sProduct);
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, softwareMicrosoft);
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, softwareAcrobat);
		productDetailsPage.clickToRadioAndCheckboxByLabel(driver, softwareTotal);

		productDetailsPage.clickToButtonByName(driver, "Add to cart");

		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("Wishlist_01 - Step 04: Verify message is displayed");
		verifyEquals(productDetailsPage.getMessageInProductDetailsDisplayedByText(driver),"The product has been added to your shopping cart");

		log.info("Wishlist_01 - Step 05: Click to Close icon");
		productDetailsPage.clickToCloseIconInMessage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);

		productDetailsPage.hoverToShoppingCartHeaderMenu();
		replaceUnitPrice = productDetailsPage.getPriceUnit();
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("There are 1 item(s) in your cart."));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass(" Processor: " + processorProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("RAM: " + ramProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("HDD: " + hddProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("OS: " + sProduct));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Software: " + softwareMicrosoft));
		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Unit price: " + String.valueOf(replaceUnitPrice)));
//		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Quantity: " + quantity));
//		verifyTrue(productDetailsPage.isProductInfoInMiniShoppingCartHeaderByClass("Sub-Total: " + totalPrice));
	}
	
	@Test
	public void Edit_Product_To_Cart_02() {
		productDetailsPage.clickToButtonByName(driver, "Go to cart");
		shoppingCartPO = PageGenerator.getShoppingCartPage(driver);
		
		
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
	ShoppingCartPO shoppingCartPO;

}

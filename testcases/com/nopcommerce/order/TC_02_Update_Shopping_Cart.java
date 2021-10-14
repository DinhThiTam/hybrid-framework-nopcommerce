
package com.nopcommerce.order;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
import pageUIs.nopCommerce.ProductDetailsPageUI;
import utilities.DataUtil;

public class TC_02_Update_Shopping_Cart extends BaseTest {
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
	public void TC_04_Update_Shopping_Cart() {
		log.info("Wishlist_01 - Step 01: Open sub menu 'Desktops '");
		registerPage = PageGenerator.getRegisterPage(driver);
		registerPage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
		desktopsPage = PageGenerator.getDesktopsPage(driver);

		desktopsPage.clickToProductLinkByText(driver, "Lenovo IdeaCentre 600 All-in-One PC");
		desktopsPage.sleepInsecond(3);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage.clickToButtonByClassAndName(driver, "add-to-cart", "Add to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage.openMenuFooterPageByName(driver, "Shopping cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		verifyEquals(shoppingCartPage.getValueInTableIDAtColumnHorizontalNameAndRowIndex(driver, "cart", "1", "Total"),"$500.00");
	
		
		quantity = 5;
		shoppingCartPage.enterToInputQuantityTextbox(String.valueOf(quantity));
		shoppingCartPage.sleepInsecond(3);
		shoppingCartPage.clickToButtonByName(driver, "Update shopping cart");
		shoppingCartPage.sleepInsecond(3);
		
		verifyEquals(shoppingCartPage.getValueInTableIDAtColumnHorizontalNameAndRowIndex(driver, "cart", "1", "Total"),"$2,500.00");
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

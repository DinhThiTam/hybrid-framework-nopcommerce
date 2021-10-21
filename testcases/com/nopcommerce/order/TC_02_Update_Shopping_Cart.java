
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
	public void TC_01_Update_Shopping_Cart() {
		log.info("TC_02 - Step 01: Open sub menu 'Desktops '");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Desktops ");
		desktopsPage = PageGenerator.getDesktopsPage(driver);

		log.info("TC_02 - Step 02: Click to the product link");
		desktopsPage.clickToProductLinkByText(driver, "Lenovo IdeaCentre 600 All-in-One PC");
		desktopsPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		
		log.info("TC_02 - Step 03: Click to 'Add to cart'");
		productDetailsPage.clickToButtonByClassAndName(driver, "add-to-cart", "Add to cart");
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_02 - Step 04: Open 'Shopping cart' page");
		productDetailsPage.openMenuFooterPageByName(driver, "Shopping cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		
		log.info("TC_02 - Step 05: Verify the product is displayed in the table");
		verifyEquals(shoppingCartPage.getValueInTableIDAtColumnHorizontalNameAndRowIndex(driver, "cart", "1", "Total"),"$500.00");
	
		quantity = 5;
		
		log.info("TC_02 - Step 06: Enter to input quantity textbox");
		shoppingCartPage.enterToInputQuantityTextbox(String.valueOf(quantity));
		shoppingCartPage.sleepInsecond(3);
		
		log.info("TC_02 - Step 07: Click to 'Update shopping cart' button");
		shoppingCartPage.clickToButtonByName(driver, "Update shopping cart");
		shoppingCartPage.sleepInsecond(3);
		
		log.info("TC_02 - Step 08: Verify the total price is updated");
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

package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_02_Login_Admin;

import commons.BaseTest;
import pageObject.admin.nopCommerce.DashboardPO;
import pageObject.admin.nopCommerce.LoginPO;
import pageObject.admin.nopCommerce.PageGenerator;
import pageObject.admin.nopCommerce.ProductDetailsPO;
import pageObject.admin.nopCommerce.ProductSearchPO;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;
import utilities.DataUtil;


public class TC_01_Catalog_Products extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String productName, catagory, parentCatagory, childCatagory, manufacturer, skuProduct;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		catagory = "All";
		parentCatagory = "Computers";
		childCatagory = "Computers >> Desktops";
		manufacturer = "Apple";
		skuProduct = "LE_IC_600";
		
		loginPage = PageGenerator.getLoginPage(driver);
		verifyTrue(loginPage.isLoginPageTitleAdminDisplayed());
		
//		log.info("Pre-Condition - Step 02: Set login page cookie");
//		loginPage.setAllCookies(driver, Common_02_Login_Admin.loginPageCookie);
//		loginPage.sleepInsecond(5);
//		loginPage.refreshPage(driver);
		
		log.info("Pre-Condition - Step 03: Open homepage");
		loginPage.clickToButtonByName(driver, "Log in");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		
	}
	@Test
	public void TC_01_Search_With_Product_Name() {
		log.info("Pre-Condition - Step 05: Open sub menu 'Product'");
		dashboardPage.openSubMenuPageByName(driver, "Catalog","Products");
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		productSearchPage = PageGenerator.getProductSearchPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Product name"),productName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "SKU"),"LE_IC_600");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Price"),"500");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Stock quantity"),"10000");
		String getPublishedValue = productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published"),getPublishedValue);
	}
	
	//@Test
	public void TC_02_Search_With_Product_Name_ParentsCatalogue_Unchecked() {
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, parentCatagory, "SearchCategoryId");
		//productSearchPage.clickToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.uncheckedToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		verifyTrue(productSearchPage.isMessageInTableDisplayed());
	
	}
	
	//@Test
	public void TC_03_Search_With_Product_Name_ParentsCatalogue_Checked() {
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, parentCatagory, "SearchCategoryId");
		productSearchPage.clickToCheckboxOrRadioAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Product name"),productName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "SKU"),"LE_IC_600");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Price"),"500");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Stock quantity"),"10000");
		String getPublishedValue = productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published"),getPublishedValue);
	
	}
	
	//@Test
	public void TC_04_Search_With_Product_Name_ChildCatalogue() {
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, childCatagory, "SearchCategoryId");
		productSearchPage.uncheckedToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Product name"),productName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "SKU"),"LE_IC_600");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Price"),"500");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Stock quantity"),"10000");
		String getPublishedValue = productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published"),getPublishedValue);
	
	}
	
	//@Test
	public void TC_05_Search_With_Product_Name_Manufacturer() {
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, catagory, "SearchCategoryId");
		productSearchPage.uncheckedToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, manufacturer, "SearchManufacturerId");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		verifyTrue(productSearchPage.isMessageInTableDisplayed());
	}
	
	@Test
	public void TC_06_Go_product_SKU() {
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Enter to 'Go directly to product' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "GoDirectlyToSku", skuProduct);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		
		log.info("Pre-Condition - Step 05: Click to 'Go' button");
		productSearchPage.clickToButtonByID(driver,"go-to-product-by-sku");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		verifyTrue(productDetailsPage.isFormTitleProductDetailsDisplayed());
		verifyEquals(productDetailsPage.getValueTextboxInForm(driver, "value", "Name"), "Lenovo IdeaCentre 600 All-in-One PC");
	
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
	}
	WebDriver driver;
	LoginPO loginPage;
	DashboardPO dashboardPage;
	ProductSearchPO productSearchPage;
	ProductDetailsPO productDetailsPage;

}

package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObject.admin.nopCommerce.DashboardPO;
import pageObject.admin.nopCommerce.LoginPO;
import pageObject.admin.nopCommerce.PageGenerator;
import pageObject.admin.nopCommerce.ProductDetailsPO;
import pageObject.admin.nopCommerce.ProductSearchPO;


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
		
		log.info("Pre-Condition - Step 03: Open homepage");
		loginPage.clickToButtonByName(driver, "Log in");
		loginPage.isJQueryAjaxLoadedSuccess(driver);
		dashboardPage = PageGenerator.getDashboardPage(driver);
		
	}
	@Test
	public void TC_01_Search_With_Product_Name() {
		log.info("TC_01 - Step 01: Open sub menu 'Product'");
		dashboardPage.openSubMenuPageByName(driver, "Catalog","Products");
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		productSearchPage = PageGenerator.getProductSearchPage(driver);
		
		log.info("TC_01 - Step 02: Expand Panel 'Search'");
		productSearchPage.clickToExpandPanelSearch();
		
		log.info("TC_01 - Step 03: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 04: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 05: Verify only one item is displayed in table");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Product name"),productName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "SKU"),"LE_IC_600");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Price"),"500");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Stock quantity"),"10000");
		String getPublishedValue = productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published"),getPublishedValue);
	}
	
	@Test
	public void TC_02_Search_With_Product_Name_ParentsCatalogue_Unchecked() {
		log.info("TC_02 - Step 01: Refresh page");
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_02 - Step 02: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		
		log.info("TC_02 - Step 03: Select item in dropdown");
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, parentCatagory, "SearchCategoryId");
		
		log.info("TC_02 - Step 03: Uncheck to checkbox");
		productSearchPage.uncheckedToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_02 - Step 04: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		
		log.info("TC_02 - Step 05: Verify 'No data available in table' message is dispayed ");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		verifyTrue(productSearchPage.isMessageInTableDisplayed());
	
	}
	
	@Test
	public void TC_03_Search_With_Product_Name_ParentsCatalogue_Checked() {
		log.info("TC_03 - Step 01: Refresh page");
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_03 - Step 02: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		
		log.info("TC_03 - Step 03: Select item in dropdown");
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, parentCatagory, "SearchCategoryId");
		
		log.info("TC_03 - Step 04: Check to 'Search subcategories' checkbox");
		productSearchPage.clickToCheckboxOrRadioAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_03 - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_03 - Step 06: Verify only one item is displayed in table");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Product name"),productName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "SKU"),"LE_IC_600");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Price"),"500");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Stock quantity"),"10000");
		String getPublishedValue = productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published"),getPublishedValue);
	
	}
	
	@Test
	public void TC_04_Search_With_Product_Name_ChildCatalogue() {
		log.info("TC_04 - Step 01: Refresh page");
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_04 - Step 02: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		
		log.info("TC_04 - Step 03: Select to Child Category in dropdown");
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, childCatagory, "SearchCategoryId");
		
		log.info("TC_04 - Step 04: Uncheck to 'Search subcategories' checkbox");
		productSearchPage.uncheckedToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_04 - Step 05: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_04 - Step 06: Verify only one item is displayed in table");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Product name"),productName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "SKU"),"LE_IC_600");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Price"),"500");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Stock quantity"),"10000");
		String getPublishedValue = productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published");
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Published"),getPublishedValue);
	
	}
	
	@Test
	public void TC_05_Search_With_Product_Name_Manufacturer() {
		log.info("TC_05 - Step 01: Refresh page");
		productSearchPage.refreshPage(driver);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_05 - Step 02: Enter to 'Product name' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchProductName", productName);
		
		log.info("TC_05 - Step 03: Select to Category in dropdown");
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, catagory, "SearchCategoryId");
		
		log.info("TC_05 - Step 04: Uncheck to 'Search subcategories' checkbox");
		productSearchPage.uncheckedToCheckboxAtAdminSite(driver, "SearchIncludeSubCategories");
		
		log.info("TC_05 - Step 05: Select to Manufacturer dropdown");
		productSearchPage.selectItemInDropdownByNameAtAdminSite(driver, manufacturer, "SearchManufacturerId");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_05 - Step 06: Click to 'Search' button");
		productSearchPage.clickToButtonByID(driver,"search-products");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_05 - Step 07: Verify only one item is displayed in table");
		verifyTrue(productSearchPage.isMessageInTableDisplayed());
	}
	
	@Test
	public void TC_06_Go_product_SKU() {
		log.info("TC_06 - Step 01: Refresh page");
		productSearchPage.refreshPage(driver);
		
		
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_06 - Step 02: Enter to 'Go directly to product' textbox");
		productSearchPage.enterToTextboxByIDAtAdminSite(driver, "GoDirectlyToSku", skuProduct);
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_06 - Step 03: Click to 'Go' button");
		productSearchPage.clickToButtonByID(driver,"go-to-product-by-sku");
		productSearchPage.isJQueryAjaxLoadedSuccess(driver);
		productDetailsPage = PageGenerator.getProductDetailsPage(driver);
		productDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_06 - Step 04: Verify Title product details page is displayed");
		verifyTrue(productDetailsPage.isFormTitleProductDetailsDisplayed());
		
		log.info("TC_06 - Step 05: Verify product info is displayed");
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

package com.nopcommerce.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.nopCommerce.HomePO;
import pageObject.nopCommerce.NotebooksPO;
import pageObject.nopCommerce.PageGenerator;


public class TC_01_Sort_Display_Paging extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGenerator.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
		log.info("Pre-Condition - Step 02: Open sub menu on top-menu");
		homePage.openSubMenuPage(driver, "top-menu notmobile", "Computers ", "Notebooks ");
		notebooksPage = PageGenerator.getNotebooksPage(driver);
	}
	@Test
	public void TC_01_Sort_By_Name_A_Z() {
		log.info("Sort_01 - Step 01: Select item in sort dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "Name: A to Z", "products-orderby");
		notebooksPage.sleepInsecond(2);
		
		log.info("Sort_01 - Step 02: Verify product names are sorted in ascending order");
		verifyTrue(notebooksPage.isProductNameSortAscending());
	}

	@Test
	public void TC_02_Sort_By_Name_Z_A() {
		log.info("Sort_01 - Step 01: Select item in sort dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "Name: Z to A", "products-orderby");
		notebooksPage.sleepInsecond(2);
		
		log.info("Sort_01 - Step 02: Verify product names are sorted in descending order");
		verifyTrue(notebooksPage.isProductNameSortDescending());
	}
	
	@Test
	public void TC_03_Sort_By_Price_Low_To_Hight() {
		log.info("Sort_01 - Step 01: Select item in sort dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "Price: Low to High", "products-orderby");
		notebooksPage.sleepInsecond(2);
		
		log.info("Sort_01 - Step 02: Verify product prices are sorted in descending order");
		verifyTrue(notebooksPage.isPriceSortAscending());
	}
	@Test
	public void TC_04_Sort_By_Price_Hight_To_Low() {
		log.info("Sort_01 - Step 01: Select item in sort dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "Price: High to Low", "products-orderby");
		notebooksPage.sleepInsecond(2);
		
		log.info("Sort_01 - Step 02: Verify product prices are sorted in descending order");
		verifyTrue(notebooksPage.isPriceSortDescending());
	}
	
	@Test
	public void TC_05_Display_Three_Per_Page() {
		log.info("TC_05 - Step 01:Select item in dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "3", "products-pagesize");
		
		log.info("TC_05 - Step 02: Verify item is selected");
		verifyEquals(notebooksPage.getSelectItemInDropdownByName(driver, "products-pagesize"), "3");
		notebooksPage.sleepInsecond(3);
		
		log.info("TC_05 - Step 03: Verify product size is only 3 or less than 3 products");
		verifyTrue(notebooksPage.isProductSizeLessThanOrEqualToThree(driver));
		
		log.info("TC_05 - Step 04: Verify the current page is '1'");
		verifyTrue(notebooksPage.isCurrenPageDisplayed("1"));
		
		log.info("TC_05 - Step 05: Verify next icon is displayed");
		verifyTrue(notebooksPage.isNextOrPreviousPageIconDisplayed("next-page", "Next"));
	}
	
	@Test
	public void TC_06_Display_Six_Per_Page() {
		log.info("TC_06 - Step 01:Select item in dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "6", "products-pagesize");
		
		log.info("TC_06 - Step 02: Verify item is selected");
		verifyEquals(notebooksPage.getSelectItemInDropdownByName(driver, "products-pagesize"), "6");
		notebooksPage.sleepInsecond(3);
		
		log.info("TC_06 - Step 03: Verify product size is only 6 or less than 6 products");
		verifyTrue(notebooksPage.isProductSizeLessThanOrEqualToSix(driver));
		
		log.info("TC_06 - Step 04: Verify Page undispayed");
		verifyTrue(notebooksPage.isPagerUndisplayed());
	}
	
	@Test
	public void TC_07_Display_Nine_Per_Page() {
		log.info("TC_07 - Step 01:Select item in dropdown");
		notebooksPage.selectItemInDropdownByName(driver, "9", "products-pagesize");
		
		log.info("TC_07 - Step 02: Verify item is selected");
		verifyEquals(notebooksPage.getSelectItemInDropdownByName(driver, "products-pagesize"), "9");
		notebooksPage.sleepInsecond(3);
		
		log.info("TC_07 - Step 03: Verify product size is only 9 or less than 9 products");
		verifyTrue(notebooksPage.isProductSizeLessThanOrEqualToNine(driver));
		
		log.info("TC_07 - Step 04: Verify Page undispayed");
		verifyTrue(notebooksPage.isPagerUndisplayed());
	}
	
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition - Close Browser - " + browserName + "");
		cleanBrowserAndDriver();
}
	WebDriver driver;
	HomePO homePage;
	NotebooksPO notebooksPage;
}

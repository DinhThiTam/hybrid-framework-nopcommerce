package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_02_Login_Admin;

import commons.BaseTest;
import pageObject.admin.nopCommerce.AddNewCustomersPO;
import pageObject.admin.nopCommerce.CustomerDetailsPO;
import pageObject.admin.nopCommerce.CustomersSearchPO;
import pageObject.admin.nopCommerce.DashboardPO;
import pageObject.admin.nopCommerce.LoginPO;
import pageObject.admin.nopCommerce.PageGenerator;
import pageObject.admin.nopCommerce.ProductDetailsPO;
import pageObject.admin.nopCommerce.ProductSearchPO;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;
import utilities.DataUtil;


public class TC_02_Customers extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String emailAddress, password, firstName, lastName, gender, dateOfBirth, companyName, customerRole, adminComment;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName + "' and navigate '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		fakeData = DataUtil.getData();
		
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		gender = "Male";
		dateOfBirth = "1/1/2000";
		companyName = fakeData.getString();
		customerRole = "Guests";
		adminComment = fakeData.getString();
		
		loginPage = PageGenerator.getLoginPage(driver);
		verifyTrue(loginPage.isLoginPageTitleAdminDisplayed());
		
		log.info("Pre-Condition - Step 03: Open homepage");
		loginPage.clickToButtonByName(driver, "Log in");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		
	}
	@Test
	public void TC_01_Search_With_Product_Name() {
		log.info("Pre-Condition - Step 05: Open sub menu 'Customers'");
		dashboardPage.openSubMenuPageByName(driver, "Customers","Customers");
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Pre-Condition - Step 05: Click to 'Add new' button");
		customersSearchPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Customers", "Add new");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		addNewCustomersPage = PageGenerator.getAddNewCustomersPage(driver);
		addNewCustomersPage.isJQueryAjaxLoadedSuccess(driver);
		
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Email", emailAddress);
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Password", password);
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "FirstName", firstName);
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "LastName", lastName);
		addNewCustomersPage.clickToCheckboxOrRadioAtAdminSite(driver, "Gender_Male");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "DateOfBirth", dateOfBirth);
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Company", companyName);
//		addNewCustomersPage.sleepInsecond(5);
//		addNewCustomersPage.scrollToBottomPage(driver);
//		addNewCustomersPage.selectCustomerRoleInDropdown("Guests");
		
		addNewCustomersPage.enterToAdminCommentTextArea(adminComment);
		customersSearchPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Add a new customer", "Save and Continue Edit");
		customerDetailsPage= PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isAddCustomerMessageSuccessDisplayed();
		
		
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
	CustomersSearchPO customersSearchPage;
	AddNewCustomersPO addNewCustomersPage;
	CustomerDetailsPO customerDetailsPage;
	DataUtil fakeData;

}

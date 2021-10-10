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
import pageUIs.admin.nopCommerce.CustomersSearchPageUI;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;
import utilities.DataUtil;

public class TC_02_Customers extends BaseTest {
	String projectLocation = System.getProperty("user.dir");
	String emailAddress, password, firstName, lastName, gender, dateOfBirth, companyName, customerRole, adminComment,
			fullName;
	String editEmailAddress, editFirstName, editLastName, editDateOfBirth, editCompanyName, editAdminComment, editFullName;

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
		fullName = firstName + " " + lastName;
		gender = "Male";
		dateOfBirth = "1/1/2000";
		companyName = fakeData.getString();
		customerRole = "Guests";
		adminComment = fakeData.getString();
		
		editEmailAddress= "edit12346@email.vn";
		editFirstName= "edit1"; 
		editLastName= "edit2";
		editDateOfBirth= "2/2/2000";
		editCompanyName= "edit3"; 
		editAdminComment = "edit4";
		editFullName = editFirstName + " " + editLastName;
		
		loginPage = PageGenerator.getLoginPage(driver);
		verifyTrue(loginPage.isLoginPageTitleAdminDisplayed());
		
		log.info("Pre-Condition - Step 03: Open homepage");
		loginPage.clickToButtonByName(driver, "Log in");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		
	}

	@Test
	public void TC_01_Create_New_Customer() {
		log.info("Pre-Condition - Step 05: Open sub menu 'Customers'");
		dashboardPage.openSubMenuPageByName(driver, "Customers", "Customers");
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);

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
		addNewCustomersPage.sleepInsecond(2);
		addNewCustomersPage.clearDynamicDropdown(driver);
		addNewCustomersPage.sleepInsecond(2);
		addNewCustomersPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		addNewCustomersPage.clickToCheckboxOrRadioAtAdminSite(driver, "Active");

		verifyEquals(addNewCustomersPage.getItemSelected(driver), "Guests");
		addNewCustomersPage.enterToAdminCommentTextArea(driver, adminComment);
		addNewCustomersPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Add a new customer",
				"Save and Continue Edit");
		addNewCustomersPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isMessageSuccessDisplayed(driver,"The new customer has been added successfully.");
		customerDetailsPage.clickToExpandPanelByName(driver, "Customer info");
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "Email"), emailAddress);
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "FirstName"), firstName);
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "LastName"), lastName);
		verifyTrue(customerDetailsPage.isSelectedItemInRadioAtAdminSite(driver, "Gender_Male"));
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "DateOfBirth"), dateOfBirth);
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "Company"), companyName);
		verifyTrue(customerDetailsPage.isSelectedItemInRadioAtAdminSite(driver, "Active"));
		customersSearchPage = customerDetailsPage.clickToBackToCustomerListButton();
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.sleepInsecond(3);

		customersSearchPage.isRowValueInRowDisplayed(driver, "Guest", fullName, customerRole, companyName);

	}

	@Test
	public void TC_02_Search_Cusomer_With_Email() {

		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", emailAddress);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), companyName);

	}

	@Test
	public void TC_03_Search_Cusomer_With_FirstName_LastName() {

		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", firstName);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", lastName);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), companyName);

	}

	@Test
	public void TC_04_Search_Cusomer_With_Company() {

		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", companyName);

		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), companyName);

	}

	@Test
	public void TC_05_Search_Cusomer_With_Full_Data() {

		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", emailAddress);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", firstName);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", lastName);

		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "1", "SearchMonthOfBirth");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "1", "SearchDayOfBirth");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", companyName);

		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), companyName);

	}

	@Test
	public void TC_06_Edit_Customer() {

		customersSearchPage.clickToEditButtonInTable(driver, "Guest", fullName, customerRole, companyName);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		

		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "Email", editEmailAddress);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "FirstName", editFirstName);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "LastName", editLastName);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "DateOfBirth", editDateOfBirth);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "Company", editCompanyName);
		customerDetailsPage.enterToAdminCommentTextArea(driver, editAdminComment);
		customerDetailsPage.clickToButtonByNameAttribute(driver, "save");
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.isMessageSuccessDisplayed(driver, "The customer has been updated successfully.");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", editFirstName);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", editLastName);

		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", editCompanyName);
		
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), editFullName);
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), editCompanyName);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
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

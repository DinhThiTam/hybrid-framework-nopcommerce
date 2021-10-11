package com.nopcommerce.admin;

import org.apache.log4j.net.TelnetAppender;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_02_Login_Admin;

import commons.BaseTest;
import pageObject.admin.nopCommerce.AddNewAddressPO;
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
	String stateProvince, countryName, cityName, address1, address2, zipCode, citySateZip, phoneNumber, faxNumber;

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
		customerRole = "Guests";
		adminComment = fakeData.getString();
		companyName= "Automation FC";
		stateProvince= "Other"; 
		countryName="Viet Nam" ;
		cityName= "Da Nang";
		address1="123/04 Le Lai" ;
		address2= "234/05 Hai Phong"; 
		zipCode= "550000"; 
		citySateZip = cityName + ", " + zipCode;
		phoneNumber= "0123456789"; 
		faxNumber = "0983970447";
		
		editEmailAddress= fakeData.getEditEmailAddress();
		editFirstName= "dinh"; 
		editLastName= "tam";
		editDateOfBirth= "2/2/2000";
		editCompanyName= "Edit Automation FC"; 
		editAdminComment = "Edit Comment";
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
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
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
	
	@Test
	public void TC_07_Add_New_Address() {
		
		customersSearchPage.clickToEditButtonInTable(driver, "Guest", editFullName, customerRole, editCompanyName);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		 
		
		customerDetailsPage.clickToButtonByContainsText(driver, "Add new address");
		addNewAddressPage = PageGenerator.getAddNewAddressPage(driver);
		addNewAddressPage.isJQueryAjaxLoadedSuccess(driver);
		

		log.info("Add_Address_01 - Step 04: Update First name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_FirstName", firstName);
		
		log.info("Add_Address_01 - Step 05: Update Last name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_LastName", lastName);
		
		log.info("Add_Address_01 - Step 06: Update Email information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Email", emailAddress);
		
		log.info("Add_Address_01 - Step 07: Update Company name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Company", companyName);
		
		log.info("Add_Address_01 - Step 03: Update Country name information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver,countryName , "Address.CountryId");
	
		log.info("Add_Address_01 - Step 03: Update State province information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver, stateProvince, "Address.StateProvinceId");
		
		log.info("Add_Address_01 - Step 03: Update Company City name information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_City", cityName);
		
		log.info("Add_Address_01 - Step 03: Update Address 1 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address1", address1);
		
		log.info("Add_Address_01 - Step 03: Update Address 2 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address2", address2);
		
		log.info("Add_Address_01 - Step 03: Update Zip code information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", zipCode);
		
		log.info("Add_Address_01 - Step 03: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);
		
		log.info("Add_Address_01 - Step 03: Click to 'Save' button");
		addNewAddressPage.clickToButtonByContainsText(driver, "Save");
		
		addNewAddressPage.isMessageSuccessDisplayed(driver, "The new address has been added successfully.");
		
		log.info("Add_Address_01 - Step 03: Verify Firstname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_FirstName"), firstName);
		
		log.info("Add_Address_01 - Step 03: Verify Lastname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_LastName"), lastName);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_Email"), emailAddress);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_Company"), companyName);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.CountryId"), countryName);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_City"), cityName);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_Address1"), address1);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_Address2"), address2);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_ZipPostalCode"), zipCode);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByID(driver, "Address_PhoneNumber"), phoneNumber);
		
		addNewAddressPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "First name"),firstName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Last name"),lastName);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Email"),emailAddress);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Phone number"),phoneNumber);
		verifyEquals(productSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody", "dataTables_scrollHead", "1", "Address"),address1 + " " + address2);
		
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
	AddNewAddressPO addNewAddressPage;
	DataUtil fakeData;

}

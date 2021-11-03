package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.Customers.EditAddress;
import com.nopcommerce.data.Customers.NewAddress;
import com.nopcommerce.data.Customers.UpdateAddress;

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
		editEmailAddress= fakeData.getEditEmailAddress();
		
		log.info("Pre-Condition - Step 02: Verify Login Page is displayed");
		loginPage = PageGenerator.getLoginPage(driver);
		verifyTrue(loginPage.isLoginPageTitleAdminDisplayed());
		
		log.info("Pre-Condition - Step 03: Open homepage");
		loginPage.clickToButtonByName(driver, "Log in");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		
	}

	@Test
	public void TC_01_Create_New_Customer() {
		log.info("TC_01 - Step 01: Open sub menu 'Customers'");
		dashboardPage.openSubMenuPageByName(driver, "Customers", "Customers");
		dashboardPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_01 - Step 02: Click to 'Add new' button");
		customersSearchPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Customers", "Add new");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		addNewCustomersPage = PageGenerator.getAddNewCustomersPage(driver);
		
		log.info("TC_01 - Step 03: Enter to 'Email' textbox");
		addNewCustomersPage.isJQueryAjaxLoadedSuccess(driver);
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Email", emailAddress);
		
		log.info("TC_01 - Step 04: Enter to 'Password' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Password", password);
		
		log.info("TC_01 - Step 05: Enter to 'Firstname' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "FirstName", firstName);
		
		log.info("TC_01 - Step 06: Enter to 'Lastname' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "LastName", lastName);
		
		log.info("TC_01 - Step 07: Click to 'Male' radio button");
		addNewCustomersPage.clickToCheckboxOrRadioAtAdminSite(driver, "Gender_Male");
		
		log.info("TC_01 - Step 08: Enter to 'Date of birth' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "DateOfBirth", NewAddress.DATE_OF_BIRTH);
		
		log.info("TC_01 - Step 09: Enter to 'Company name' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Company", NewAddress.COMPANY_NAME);
		addNewCustomersPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 10: Clear text in dropdown");
		addNewCustomersPage.clearDynamicDropdown(driver);
		addNewCustomersPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 11: Select 'Guest' role in dropdown");
		addNewCustomersPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		
		log.info("TC_01 - Step 12: Click to 'Active' checkbox");
		addNewCustomersPage.clickToCheckboxOrRadioAtAdminSite(driver, "Active");
		
		log.info("TC_01 - Step 13: Verify 'Guests' role is selected");
		verifyEquals(addNewCustomersPage.getItemSelected(driver), "Guests");
		
		log.info("TC_01 - Step 14: Enter to 'Admin comment' textbox");
		addNewCustomersPage.enterToAdminCommentTextArea(driver, NewAddress.ADMIN_COMMENT);
		
		log.info("TC_01 - Step 15: Click to 'Save and Continue Edit' button");
		addNewCustomersPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Add a new customer",
				"Save and Continue Edit");
		addNewCustomersPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 16: Verify success mesage");
		customerDetailsPage.isMessageSuccessDisplayed(driver,"The new customer has been added successfully.");
		customerDetailsPage.clickToCloseIcon(driver);
		
		log.info("TC_01 - Step 17: Click to expand panel");
		customerDetailsPage.clickToExpandPanelByName(driver, "Customer info");
		
		log.info("TC_01 - Step 18: Verify customers info are displayed ");
		verifyEquals(customerDetailsPage.getValueTextboxInForm(driver, "value", "Email"), emailAddress);
		verifyEquals(customerDetailsPage.getValueTextboxInForm(driver, "value", "FirstName"), firstName);
		verifyEquals(customerDetailsPage.getValueTextboxInForm(driver, "value", "LastName"), lastName);
		verifyTrue(customerDetailsPage.isSelectedItemInRadioAtAdminSite(driver, "Gender_Male"));
		verifyEquals(customerDetailsPage.getValueTextboxInForm(driver, "value", "DateOfBirth"), NewAddress.DATE_OF_BIRTH);
		verifyEquals(customerDetailsPage.getValueTextboxInForm(driver, "value", "Company"), NewAddress.COMPANY_NAME);
		verifyTrue(customerDetailsPage.isSelectedItemInRadioAtAdminSite(driver, "Active"));
		
		log.info("TC_01 - Step 19: Click to 'Back to customer list' button");
		customerDetailsPage.sleepInsecond(3);
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 20: Clear 'Customer roles' droplist");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 21: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_01 - Step 22: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_01 - Step 23: Verify customer info in table is displayed");
		customersSearchPage.isRowValueInRowDisplayed(driver, "Guest", fullName, NewAddress.CUSTOMER_ROLE, NewAddress.COMPANY_NAME);
	}

	@Test
	public void TC_02_Search_Cusomer_With_Email() {
		log.info("TC_02 - Step 01: Refresh page");
		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_02 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", emailAddress);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_02 - Step 03: Clear 'Customer roles' list");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_02 - Step 04: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_02 - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_02 - Step 06: Verify 'Email' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		
		log.info("TC_02 - Step 07: Verify 'Name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		
		log.info("TC_02 - Step 08: Verify 'Guests' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		
		log.info("TC_02 - Step 09: Verify 'Company' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_03_Search_Cusomer_With_FirstName_LastName() {
		log.info("TC_03 - Step 01: Refresh page");
		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_03 - Step 02: Enter to 'First Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", firstName);
		
		log.info("TC_03 - Step 03: Enter to 'Last Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", lastName);
		
		log.info("TC_03 - Step 04: Clear 'Customer roles' list");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_03 - Step 05: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_03 - Step 06: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_03 - Step 07: Verify 'Email' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		
		log.info("TC_03 - Step 08: Verify 'Name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		
		log.info("TC_03 - Step 09: Verify 'Customer roles' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		
		log.info("TC_03 - Step 10: Verify 'Company name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_04_Search_Cusomer_With_Company() {
		log.info("TC_04 - Step 01: Refresh page");
		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_04 - Step 02: Enter to 'Company Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", NewAddress.COMPANY_NAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_04 - Step 03: Clear 'Customer roles' list");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_04 - Step 04: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_04 - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_04 - Step 06: Verify 'Email' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		
		log.info("TC_04 - Step 07: Verify 'Name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		
		log.info("TC_04 - Step 08: Verify 'Customer rolesl' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		
		log.info("TC_04 - Step 09: Verify 'Company name'' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_05_Search_Cusomer_With_Full_Data() {
		log.info("TC_05 - Step 01: Refresh page");
		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_05 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", emailAddress);
		
		log.info("TC_05 - Step 03: Enter to 'First Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", firstName);
		
		log.info("TC_05 - Step 04: Enter to 'Last Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", lastName);

		log.info("TC_05 - Step 05: Enter to 'Month of Birth' textbox");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "1", "SearchMonthOfBirth");
		
		log.info("TC_05 - Step 06: Enter to 'Day of Birth' textbox");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "1", "SearchDayOfBirth");
		
		log.info("TC_05 - Step 07: Enter to 'Company Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", NewAddress.COMPANY_NAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_05 - Step 08: Clear 'Customer roles' list");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_05 - Step 09: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_05 - Step 10: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_05 - Step 11: Verify 'Email' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		
		log.info("TC_05 - Step 12: Verify 'Name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		
		log.info("TC_05 - Step 13: Verify 'Customer roles' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		
		log.info("TC_05 - Step 14: Verify 'Company name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_06_Edit_Customer() {
		log.info("TC_06 - Step 01: Click to 'Edit' button in table");
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", fullName, NewAddress.CUSTOMER_ROLE, NewAddress.COMPANY_NAME);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.sleepInsecond(5);
		
		log.info("TC_06 - Step 02: Enter to 'Email' textbox");
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "Email", editEmailAddress);
		
		log.info("TC_06 - Step 03: Enter to 'First Name' textbox");
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "FirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_06 - Step 04: Enter to 'Last Name' textbox");
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "LastName", EditAddress.EDIT_LASTNAME);
		
		log.info("TC_06 - Step 05: Enter to 'Date of Birth' textbox");
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "DateOfBirth", EditAddress.EDIT_DATEOFBIRTH);
		
		log.info("TC_06 - Step 06: Enter to 'Company Name' textbox");
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "Company", EditAddress.EDIT_COMPANYNAME);
		
		log.info("TC_06 - Step 06: Enter to 'Comment Admin' textbox");
		customerDetailsPage.enterToAdminCommentTextArea(driver, EditAddress.EDIT_COMMENTADMIN);
		
		log.info("TC_06 - Step 07: Click to 'Save' button");
		customerDetailsPage.clickToButtonByNameAttribute(driver, "save");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		
		log.info("TC_06 - Step 08: Verify success message is displayed");
		customersSearchPage.isMessageSuccessDisplayed(driver, "The customer has been updated successfully.");
	
		log.info("TC_06 - Step 09: Enter to 'Email' textbox");
		customersSearchPage.sleepInsecond(3);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		
		log.info("TC_06 - Step 10: Enter to 'First Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_06 - Step 11: Enter to 'Last Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", EditAddress.EDIT_LASTNAME);

		log.info("TC_06 - Step 12: Enter to 'Month of Birth' textbox");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		
		log.info("TC_06 - Step 13: Enter to 'Day of Birth' textbox");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		
		log.info("TC_06 - Step 14: Enter to 'Company Name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_06 - Step 15: Clear 'Customer roles' list");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_06 - Step 16: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_06 - Step 17: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("TC_06 - Step 18: Verify 'Email' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		
		log.info("TC_06 - Step 19: Verify 'Name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), EditAddress.EDIT_FULLNAME);
		
		log.info("TC_06 - Step 20: Verify 'Guests' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		
		log.info("TC_06 - Step 21: Verify 'Company name' info in table is displayed");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), EditAddress.EDIT_COMPANYNAME);
	}
	
	@Test
	public void TC_07_Add_New_Address() {
		log.info("TC_07 - Step 01: Click to 'Edit' button in table");
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", EditAddress.EDIT_FULLNAME, NewAddress.CUSTOMER_ROLE, EditAddress.EDIT_COMPANYNAME);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
	
		log.info("TC_07 - Step 02: Click to Expand panel in 'Addresses' form");
		customerDetailsPage.sleepInsecond(3);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		customerDetailsPage.sleepInsecond(3);
		 
		log.info("TC_07 - Step 3: Click to 'Add new Address' button");
		customerDetailsPage.clickToButtonByContainsText(driver, "Add new address");
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_07 - Step 04: Update First name information to textbox");
		addNewAddressPage = PageGenerator.getAddNewAddressPage(driver);
		addNewAddressPage.sleepInsecond(3);
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_FirstName", firstName);
		
		log.info("TC_07 - Step 05: Update Last name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_LastName", lastName);
		
		log.info("TC_07 - Step 06: Update Email information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Email", emailAddress);
		
		log.info("TC_07 - Step 07: Update Company name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Company", NewAddress.COMPANY_NAME);
		
		log.info("TC_07 - Step 08: Update Country name information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver,NewAddress.COUNTRY_NAME , "Address.CountryId");
	
		log.info("TC_07 - Step 09: Update State province information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver, NewAddress.STATE_PROVINCE, "Address.StateProvinceId");
		
		log.info("TC_07 - Step 10: Update Company City name information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_City", NewAddress.CITY_NAME);
		
		log.info("TC_07 - Step 11: Update Address 1 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address1", NewAddress.ADDRESS1);
		
		log.info("TC_07 - Step 12: Update Address 2 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address2", NewAddress.ADDRESS2);
		
		log.info("TC_07 - Step 13: Update Zip code information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", NewAddress.ZIP_CODE);
		
		log.info("TC_07 - Step 14: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_PhoneNumber", NewAddress.PHONE_NUMBER);
		
		log.info("TC_07 - Step 15: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_FaxNumber", NewAddress.FAX_NUMBER);
		
		log.info("TC_07 - Step 16: Click to 'Save' button");
		addNewAddressPage.clickToButtonByContainsText(driver, "Save");
		addNewAddressPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_07 - Step 17: Verify success mesage");
		addNewAddressPage.isMessageSuccessDisplayed(driver, "The new address has been added successfully.");
		addNewAddressPage.sleepInsecond(3);
		
		log.info("TC_07 - Step 18: Verify Firstname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_FirstName"), firstName);
		
		log.info("ATC_07 - Step 19: Verify Lastname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_LastName"), lastName);
		
		log.info("TC_07 - Step 20: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_Email"), emailAddress);
		
		log.info("TC_07 - Step 21: Verify company name infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Company"), NewAddress.COMPANY_NAME);
		
		log.info("TC_07 - Step 22: Verify country name infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.CountryId"), NewAddress.COUNTRY_NAME);
		
		log.info("TC_07 - Step 23: Update State province information to dropdown");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.StateProvinceId"), NewAddress.STATE_PROVINCE);
		
		log.info("TC_07 - Step 24: Verify city name infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_City"), NewAddress.CITY_NAME);
		
		log.info("TC_07 - Step 25: Verify address1 infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Address1"), NewAddress.ADDRESS1);
		
		log.info("TC_07 - Step 26: Verify address2 infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_Address2"), NewAddress.ADDRESS2);
		
		log.info("TC_07 - Step 27: Verify zip code infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_ZipPostalCode"), NewAddress.ZIP_CODE);
		
		log.info("TC_07 - Step 28: Verify phone number infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_PhoneNumber"), NewAddress.PHONE_NUMBER);
		
		log.info("TC_07 - Step 29: Verify fax number infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_FaxNumber"), NewAddress.FAX_NUMBER);
		addNewAddressPage.sleepInsecond(3);
		
		log.info("TC_07 - Step 30: Click to 'Back to customer details' button");
		addNewAddressPage.clickToBackToCustomerListButton(driver, "back to customer details");
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_07 - Step 31: Click to Expand panel in 'Addresses' form");
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		
		log.info("TC_07 - Step 32: Verify  displayed customer info in table");
		customerDetailsPage.isRowValueInRowDisplayedAtAdminSite(firstName, lastName, emailAddress, NewAddress.PHONE_NUMBER, NewAddress.FAX_NUMBER, NewAddress.COMPANY_NAME);
	}
	
	@Test
	public void TC_08_Edit_Address() {
		log.info("TC_08 - Step 01: Click to 'Back to customer list' button");
		customerDetailsPage.sleepInsecond(3);
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customerDetailsPage.sleepInsecond(3);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		
		log.info("TC_08 - Step 02: Edit 'Email address' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		
		log.info("TC_08 - Step 03: Edit 'First name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_08 - Step 04: Edit 'Last name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", EditAddress.EDIT_LASTNAME);

		log.info("TC_08 - Step 05: Select 'Month of birth' item in dropdown");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		
		log.info("TC_08 - Step 06: Select 'Day of birth' item in dropdown");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		
		log.info("TC_08 - Step 07: Edit 'Company name' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_08 - Step 08: Clear 'Customer roles' list");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_08 - Step 09: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_08 - Step 10: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_08 - Step 11: Click to 'Edit' button in table");
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", EditAddress.EDIT_FULLNAME, NewAddress.CUSTOMER_ROLE, EditAddress.EDIT_COMPANYNAME);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_08 - Step 12: Click to Expand panel in 'Addresses' form");
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		customerDetailsPage.sleepInsecond(3);
		
		log.info("TC_08 - Step 13: Click to 'Edit' button in table");
		customerDetailsPage.clickToButtonInTableAtCustomerDetailPage("Addresses", "Edit");
		addNewAddressPage = PageGenerator.getAddNewAddressPage(driver);
		addNewAddressPage.sleepInsecond(3);
		
		log.info("TC_08 - Step 14: Update First name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_FirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_08 - Step 15: Update Last name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_LastName", EditAddress.EDIT_LASTNAME);
		
		log.info("TC_08 - Step 16: Update Email information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Email", emailAddress);
		
		log.info("TC_08 - Step 17: Update Company name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Company", EditAddress.EDIT_COMPANYNAME);
		
		log.info("TC_08 - Step 18: Update Country name information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver,UpdateAddress.UPDATE_COUNTRY_NAME , "Address.CountryId");

		log.info("TC_08 - Step 19: Update Company City name information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_City", UpdateAddress.UPDATE_CITY_NAME);
		
		log.info("TC_08 - Step 20: Update Address 1 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address1", UpdateAddress.UPDATE_ADDRESS1);
		
		log.info("TC_08 - Step 21: Update Address 2 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address2", UpdateAddress.UPDATE_ADDRESS2);
		
		log.info("TC_08 - Step 22: Update Zip code information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", UpdateAddress.UPDATE_ZIP_CODE);
		
		log.info("TC_08 - Step 23: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_PhoneNumber", UpdateAddress.UPDATE_PHONE_NUMBER);
		
		log.info("TC_08 - Step 24: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_FaxNumber", UpdateAddress.UPDATE_FAX_NUMBER);
		
		log.info("TC_08 - Step 25: Click to 'Save' button");
		addNewAddressPage.clickToButtonByContainsText(driver, "Save");
		addNewAddressPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_08 - Step 26: Verify success message is displayed");
		addNewAddressPage.isMessageSuccessDisplayed(driver, "The address has been updated successfully.");
		addNewAddressPage.sleepInsecond(3);
		
		log.info("TC_08 - Step 27: Verify Firstname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_FirstName"), EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_08 - Step 28: Verify Lastname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_LastName"), EditAddress.EDIT_LASTNAME);
		
		log.info("TC_08 - Step 29: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_Email"), emailAddress);
		
		log.info("TC_08 - Step 30: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Company"), EditAddress.EDIT_COMPANYNAME);
		
		log.info("TC_08 - Step 31: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.CountryId"), UpdateAddress.UPDATE_COUNTRY_NAME);
		
		log.info("TC_08 - Step 32: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver,"Address.StateProvinceId"), UpdateAddress.UPDATE_STATE_PROVINCE);
		
		log.info("TC_08 - Step 33: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_City"), UpdateAddress.UPDATE_CITY_NAME);
		
		log.info("TC_08 - Step 34: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Address1"), UpdateAddress.UPDATE_ADDRESS1);
		
		log.info("TC_08 - Step 35: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_Address2"), UpdateAddress.UPDATE_ADDRESS2);
		
		log.info("TC_08 - Step 36: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_ZipPostalCode"), UpdateAddress.UPDATE_ZIP_CODE);
		
		log.info("TC_08 - Step 37: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_PhoneNumber"), UpdateAddress.UPDATE_PHONE_NUMBER);
		
		log.info("TC_08 - Step 39: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_FaxNumber"), UpdateAddress.UPDATE_FAX_NUMBER);
		addNewAddressPage.sleepInsecond(3);
		
		log.info("TC_08 - Step 40: Click 'back to customer details' button");
		addNewAddressPage.clickToBackToCustomerListButton(driver, "back to customer details");
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_08 - Step 41: Click to Expand panel in 'Addresses' form");
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		
		log.info("TC_08 - Step 42: Verify success message is displayed");
		customerDetailsPage.isRowValueInRowDisplayedAtAdminSite(EditAddress.EDIT_FIRSTNAME, EditAddress.EDIT_LASTNAME, emailAddress, UpdateAddress.UPDATE_PHONE_NUMBER, UpdateAddress.UPDATE_FAX_NUMBER, EditAddress.EDIT_COMPANYNAME);
	}
	
	@Test
	public void TC_09_Delete_Address() {
		log.info("TC_09 - Step 01: Click to 'back to customer list' button");
		customerDetailsPage.sleepInsecond(3);
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customerDetailsPage.sleepInsecond(3);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
	
		log.info("TC_09 - Step 02: Update email information to textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		
		log.info("TC_09 - Step 03: Update first name information to textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("TC_09 - Step 04: Update lastname information to textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", EditAddress.EDIT_LASTNAME);

		log.info("TC_09 - Step 05: Update month of birth information to textbox");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		
		log.info("TC_09 - Step 06: Update day of birth information to textbox");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		
		log.info("TC_09 - Step 07: Update company name information to textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", EditAddress.EDIT_COMPANYNAME);
		
		log.info("TC_06 - Step 08: Clear 'Customer roles' list");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_09 - Step 09: Select 'Guests' item in dropdown");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("TC_09 - Step 10: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_09 - Step 11: Click to 'Edit' button in table");
		customersSearchPage.scrollToBottomPage(driver);
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", EditAddress.EDIT_FULLNAME, NewAddress.CUSTOMER_ROLE, EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		
		log.info("TC_09 - Step 12: Click to Expand panel in 'Addresses' form");
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		customerDetailsPage.clickToButtonInTableAtCustomerDetailPage("Addresses", "Delete");
		
		log.info("TC_09 - Step 13: Accept alert");
		customerDetailsPage.acceptAlert(driver);
		
		log.info("TC_09 - Step 14: Verify message in table is displayed");
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.isMessageInTableDisplayedByCardTitle(driver, "Addresses");
		
		
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

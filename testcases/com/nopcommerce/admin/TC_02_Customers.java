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

		log.info("TC_01 - Step 02: Click to 'Add new' button");
		customersSearchPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Customers", "Add new");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		addNewCustomersPage = PageGenerator.getAddNewCustomersPage(driver);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Email", emailAddress);
		
		log.info("TC_01 - Step 02: Enter to 'Password' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Password", password);
		
		log.info("TC_01 - Step 02: Enter to 'Firstname' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "FirstName", firstName);
		
		log.info("TC_01 - Step 02: Enter to 'Lastname' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "LastName", lastName);
		
		log.info("TC_01 - Step 02: Click to 'Male' radio button");
		addNewCustomersPage.clickToCheckboxOrRadioAtAdminSite(driver, "Gender_Male");
		
		log.info("TC_01 - Step 02: Enter to 'Date of birth' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "DateOfBirth", NewAddress.DATE_OF_BIRTH);
		
		log.info("TC_01 - Step 02: Enter to 'Company name' textbox");
		addNewCustomersPage.enterToTextboxByIDAtAdminSite(driver, "Company", NewAddress.COMPANY_NAME);
		addNewCustomersPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 02: Clear text in dropdown");
		addNewCustomersPage.clearDynamicDropdown(driver);
		addNewCustomersPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 02: Select 'Guest' role in dropdown");
		addNewCustomersPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		
		log.info("TC_01 - Step 02: Click to 'Active' checkbox");
		addNewCustomersPage.clickToCheckboxOrRadioAtAdminSite(driver, "Active");
		
		log.info("TC_01 - Step 02: Verify 'Guests' role is selected");
		verifyEquals(addNewCustomersPage.getItemSelected(driver), "Guests");
		
		log.info("TC_01 - Step 02: Enter to 'Admin comment' textbox");
		addNewCustomersPage.enterToAdminCommentTextArea(driver, NewAddress.ADMIN_COMMENT);
		
		log.info("TC_01 - Step 02: Click to 'Save and Continue Edit' button");
		addNewCustomersPage.clickToButtonInHeaderOnSubMenuPageByText(driver, "Add a new customer",
				"Save and Continue Edit");
		addNewCustomersPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		
		log.info("TC_01 - Step 02: Verify success mesage");
		customerDetailsPage.isMessageSuccessDisplayed(driver,"The new customer has been added successfully.");
		
		log.info("TC_01 - Step 02: Click to expand panel");
		customerDetailsPage.clickToExpandPanelByName(driver, "Customer info");
		
		log.info("TC_01 - Step 02: Verify ");
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "Email"), emailAddress);
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "FirstName"), firstName);
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "LastName"), lastName);
		verifyTrue(customerDetailsPage.isSelectedItemInRadioAtAdminSite(driver, "Gender_Male"));
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "DateOfBirth"), NewAddress.DATE_OF_BIRTH);
		verifyEquals(addNewCustomersPage.getValueTextboxInForm(driver, "value", "Company"), NewAddress.COMPANY_NAME);
		verifyTrue(customerDetailsPage.isSelectedItemInRadioAtAdminSite(driver, "Active"));
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.sleepInsecond(3);

		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.isRowValueInRowDisplayed(driver, "Guest", fullName, NewAddress.CUSTOMER_ROLE, NewAddress.COMPANY_NAME);
	}

	@Test
	public void TC_02_Search_Cusomer_With_Email() {
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", emailAddress);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Email"), "Guest");
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Name"), fullName);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_03_Search_Cusomer_With_FirstName_LastName() {
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", firstName);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", lastName);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		
		log.info("TC_01 - Step 02: Enter to 'Email' textbox");
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
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_04_Search_Cusomer_With_Company() {

		customersSearchPage.refreshPage(driver);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", NewAddress.COMPANY_NAME);

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
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

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
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", NewAddress.COMPANY_NAME);

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
				"dataTables_scrollHead", "1", "Company name"), NewAddress.COMPANY_NAME);

	}

	@Test
	public void TC_06_Edit_Customer() {

		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", fullName, NewAddress.CUSTOMER_ROLE, NewAddress.COMPANY_NAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		

		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "Email", editEmailAddress);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "FirstName", EditAddress.EDIT_FIRSTNAME);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "LastName", EditAddress.EDIT_LASTNAME);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "DateOfBirth", EditAddress.EDIT_DATEOFBIRTH);
		customerDetailsPage.enterToTextboxByIDAtAdminSite(driver, "Company", EditAddress.EDIT_COMPANYNAME);
		customerDetailsPage.enterToAdminCommentTextArea(driver, EditAddress.EDIT_COMMENTADMIN);
		
		customerDetailsPage.clickToButtonByNameAttribute(driver, "save");
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.isMessageSuccessDisplayed(driver, "The customer has been updated successfully.");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", EditAddress.EDIT_FIRSTNAME);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", EditAddress.EDIT_LASTNAME);

		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", EditAddress.EDIT_COMPANYNAME);
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
				"dataTables_scrollHead", "1", "Name"), EditAddress.EDIT_FULLNAME);
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Customer roles"), "Guests");
		verifyEquals(customersSearchPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dataTables_scrollBody",
				"dataTables_scrollHead", "1", "Company name"), EditAddress.EDIT_COMPANYNAME);
	}
	
	@Test
	public void TC_07_Add_New_Address() {
		customersSearchPage.scrollToBottomPage(driver);
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", EditAddress.EDIT_FULLNAME, NewAddress.CUSTOMER_ROLE, EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		 
		
		customerDetailsPage.clickToButtonByContainsText(driver, "Add new address");
		customerDetailsPage.sleepInsecond(5);
	
		addNewAddressPage = PageGenerator.getAddNewAddressPage(driver);
		addNewAddressPage.isJQueryAjaxLoadedSuccess(driver);
		

		log.info("Add_Address_01 - Step 04: Update First name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_FirstName", firstName);
		
		log.info("Add_Address_01 - Step 05: Update Last name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_LastName", lastName);
		
		log.info("Add_Address_01 - Step 06: Update Email information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Email", emailAddress);
		
		log.info("Add_Address_01 - Step 07: Update Company name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Company", NewAddress.COMPANY_NAME);
		
		log.info("Add_Address_01 - Step 03: Update Country name information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver,NewAddress.COUNTRY_NAME , "Address.CountryId");
	
		log.info("Add_Address_01 - Step 03: Update State province information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver, NewAddress.STATE_PROVINCE, "Address.StateProvinceId");
		
		log.info("Add_Address_01 - Step 03: Update Company City name information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_City", NewAddress.CITY_NAME);
		
		log.info("Add_Address_01 - Step 03: Update Address 1 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address1", NewAddress.ADDRESS1);
		
		log.info("Add_Address_01 - Step 03: Update Address 2 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address2", NewAddress.ADDRESS2);
		
		log.info("Add_Address_01 - Step 03: Update Zip code information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", NewAddress.ZIP_CODE);
		
		log.info("Add_Address_01 - Step 03: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_PhoneNumber", NewAddress.PHONE_NUMBER);
		
		log.info("Add_Address_01 - Step 03: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_FaxNumber", NewAddress.FAX_NUMBER);
		
		log.info("Add_Address_01 - Step 03: Click to 'Save' button");
		addNewAddressPage.clickToButtonByContainsText(driver, "Save");
		addNewAddressPage.isJQueryAjaxLoadedSuccess(driver);
		
		addNewAddressPage.isMessageSuccessDisplayed(driver, "The new address has been added successfully.");
		addNewAddressPage.sleepInsecond(3);
		
		log.info("Add_Address_01 - Step 03: Verify Firstname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_FirstName"), firstName);
		
		log.info("Add_Address_01 - Step 03: Verify Lastname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_LastName"), lastName);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_Email"), emailAddress);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Company"), NewAddress.COMPANY_NAME);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.CountryId"), NewAddress.COUNTRY_NAME);
		
		log.info("Add_Address_01 - Step 03: Update State province information to dropdown");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.StateProvinceId"), NewAddress.STATE_PROVINCE);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_City"), NewAddress.CITY_NAME);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Address1"), NewAddress.ADDRESS1);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_Address2"), NewAddress.ADDRESS2);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_ZipPostalCode"), NewAddress.ZIP_CODE);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_PhoneNumber"), NewAddress.PHONE_NUMBER);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_FaxNumber"), NewAddress.FAX_NUMBER);
		addNewAddressPage.sleepInsecond(3);
		
		addNewAddressPage.clickToBackToCustomerListButton(driver, "back to customer details");
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		//customerDetailsPage.scrollToBottomPage(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		
		customerDetailsPage.isRowValueInRowDisplayedAtAdminSite(firstName, lastName, emailAddress, NewAddress.PHONE_NUMBER, NewAddress.FAX_NUMBER, NewAddress.COMPANY_NAME);
		
	}
	
	@Test
	public void TC_08_Add_New_Address() {
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customersSearchPage.sleepInsecond(3);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.sleepInsecond(3);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", EditAddress.EDIT_FIRSTNAME);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", EditAddress.EDIT_LASTNAME);

		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		customersSearchPage.scrollToBottomPage(driver);
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", EditAddress.EDIT_FULLNAME, NewAddress.CUSTOMER_ROLE, EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		customerDetailsPage.clickToButtonInTableAtCustomerDetailPage("Addresses", "Edit");
		
		addNewAddressPage = PageGenerator.getAddNewAddressPage(driver);
		addNewAddressPage.sleepInsecond(3);
		log.info("Add_Address_01 - Step 04: Update First name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_FirstName", EditAddress.EDIT_FIRSTNAME);
		
		log.info("Add_Address_01 - Step 05: Update Last name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_LastName", EditAddress.EDIT_LASTNAME);
		
		log.info("Add_Address_01 - Step 06: Update Email information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Email", emailAddress);
		
		log.info("Add_Address_01 - Step 07: Update Company name information to textbox");
		addNewAddressPage.enterToTextboxByIDAtAdminSite(driver, "Address_Company", EditAddress.EDIT_COMPANYNAME);
		
		log.info("Add_Address_01 - Step 03: Update Country name information to dropdown");
		addNewAddressPage.selectItemInDropdownByName(driver,UpdateAddress.UPDATE_COUNTRY_NAME , "Address.CountryId");

		log.info("Add_Address_01 - Step 03: Update Company City name information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_City", UpdateAddress.UPDATE_CITY_NAME);
		
		log.info("Add_Address_01 - Step 03: Update Address 1 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address1", UpdateAddress.UPDATE_ADDRESS1);
		
		log.info("Add_Address_01 - Step 03: Update Address 2 information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_Address2", UpdateAddress.UPDATE_ADDRESS2);
		
		log.info("Add_Address_01 - Step 03: Update Zip code information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", UpdateAddress.UPDATE_ZIP_CODE);
		
		log.info("Add_Address_01 - Step 03: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_PhoneNumber", UpdateAddress.UPDATE_PHONE_NUMBER);
		
		log.info("Add_Address_01 - Step 03: Update Phone number information to textbox");
		addNewAddressPage.enterToTextboxByID(driver, "Address_FaxNumber", UpdateAddress.UPDATE_FAX_NUMBER);
		
		log.info("Add_Address_01 - Step 03: Click to 'Save' button");
		addNewAddressPage.clickToButtonByContainsText(driver, "Save");
		addNewAddressPage.isJQueryAjaxLoadedSuccess(driver);
		
		addNewAddressPage.isMessageSuccessDisplayed(driver, "The address has been updated successfully.");
		addNewAddressPage.sleepInsecond(3);
		
		log.info("Add_Address_01 - Step 03: Verify Firstname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_FirstName"), EditAddress.EDIT_FIRSTNAME);
		
		log.info("Add_Address_01 - Step 03: Verify Lastname infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_LastName"), EditAddress.EDIT_LASTNAME);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value","Address_Email"), emailAddress);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Company"), EditAddress.EDIT_COMPANYNAME);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver, "Address.CountryId"), UpdateAddress.UPDATE_COUNTRY_NAME);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getSelectItemInDropdownByName(driver,"Address.StateProvinceId"), UpdateAddress.UPDATE_STATE_PROVINCE);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_City"), UpdateAddress.UPDATE_CITY_NAME);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_Address1"), UpdateAddress.UPDATE_ADDRESS1);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver,"value", "Address_Address2"), UpdateAddress.UPDATE_ADDRESS2);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_ZipPostalCode"), UpdateAddress.UPDATE_ZIP_CODE);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_PhoneNumber"), UpdateAddress.UPDATE_PHONE_NUMBER);
		
		log.info("Add_Address_01 - Step 03: Verify email infomation is updated successfully");
		verifyEquals(addNewAddressPage.getTextboxValueByIDAtAdminSite(driver, "value","Address_FaxNumber"), UpdateAddress.UPDATE_FAX_NUMBER);
		addNewAddressPage.sleepInsecond(3);
		
		addNewAddressPage.clickToBackToCustomerListButton(driver, "back to customer details");
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		
		customerDetailsPage.isRowValueInRowDisplayedAtAdminSite(EditAddress.EDIT_FIRSTNAME, EditAddress.EDIT_LASTNAME, emailAddress, UpdateAddress.UPDATE_PHONE_NUMBER, UpdateAddress.UPDATE_FAX_NUMBER, EditAddress.EDIT_COMPANYNAME);
		
	}
	
	@Test
	public void TC_09_Add_New_Address() {
		
		customerDetailsPage.clickToBackToCustomerListButton(driver, "back to customer list");
		customersSearchPage.sleepInsecond(3);
		customersSearchPage = PageGenerator.getCustomersSearchPage(driver);
		customersSearchPage.sleepInsecond(3);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchEmail", editEmailAddress);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchFirstName", EditAddress.EDIT_FIRSTNAME);
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchLastName", EditAddress.EDIT_LASTNAME);

		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2", "SearchMonthOfBirth");
		customersSearchPage.selectItemInDropdownByNameAtAdminSite(driver, "2","SearchDayOfBirth");
		customersSearchPage.enterToTextboxByIDAtAdminSite(driver, "SearchCompany", EditAddress.EDIT_COMPANYNAME);
		
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customersSearchPage.clearDynamicDropdown(driver);
		customersSearchPage.sleepInsecond(2);
		customersSearchPage.selectCustomerRoleInDropdown(driver, "Customer roles", "Guests");
		customersSearchPage.sleepInsecond(3);
		
		log.info("Pre-Condition - Step 05: Click to 'Search' button");
		customersSearchPage.clickToButtonByID(driver, "search-customers");
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		
		customersSearchPage.scrollToBottomPage(driver);
		customersSearchPage.clickToEditButtonInTableAtSearchPage(driver, "Guest", EditAddress.EDIT_FULLNAME, NewAddress.CUSTOMER_ROLE, EditAddress.EDIT_COMPANYNAME);
		customersSearchPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage = PageGenerator.getCustomerDetailsPage(driver);
		customerDetailsPage.isJQueryAjaxLoadedSuccess(driver);
		customerDetailsPage.clickToExpandPanelByName(driver, "Addresses");
		customerDetailsPage.clickToButtonInTableAtCustomerDetailPage("Addresses", "Delete");
		
		customerDetailsPage.acceptAlert(driver);
		
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

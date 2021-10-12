package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AddNewCustomersPageUI;
import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.admin.nopCommerce.CustomersDetailsPageUI;


public class CustomerDetailsPO extends BasePage{
	private WebDriver driver;

	public CustomerDetailsPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isRowValueInRowDisplayedAtAdminSite(String firstName, String lastName, String emailAddress,
			String phoneNumber,String faxNumber,String address) {
		waitForElementVisible(driver, CustomersDetailsPageUI.ROW_VALUE_BY_FIRSTNAME_LASTNAME_EMAIL_PHONE_FAX_ADDRESS, firstName,lastName,emailAddress,phoneNumber,faxNumber, address);
		return isElementDisplayed(driver, CustomersDetailsPageUI.ROW_VALUE_BY_FIRSTNAME_LASTNAME_EMAIL_PHONE_FAX_ADDRESS, firstName,lastName,emailAddress,phoneNumber,faxNumber, address);
	}
	




	


	



	


	

	


	
	

}

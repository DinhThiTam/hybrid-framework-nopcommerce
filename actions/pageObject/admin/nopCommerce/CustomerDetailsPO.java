package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AddNewCustomersPageUI;
import pageUIs.admin.nopCommerce.CustomersDetailsPageUI;


public class CustomerDetailsPO extends BasePage{
	private WebDriver driver;

	public CustomerDetailsPO(WebDriver driver) {
		this.driver = driver;
	}
	

	public boolean isAddCustomerMessageSuccessDisplayed() {
		waitForElementVisible(driver, CustomersDetailsPageUI.ADD_CUSTOMER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, CustomersDetailsPageUI.ADD_CUSTOMER_SUCCESS_MESSAGE);
		
	}
	



	


	

	


	
	

}

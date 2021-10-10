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
	


	public CustomersSearchPO clickToBackToCustomerListButton() {
		waitForElementClickable(driver, CustomersDetailsPageUI.BACK_BUTTON);
		clickToElement(driver, CustomersDetailsPageUI.BACK_BUTTON);
		return new CustomersSearchPO(driver);
	}


	



	


	

	


	
	

}

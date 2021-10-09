package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AddNewCustomersPageUI;


public class AddNewCustomersPO extends BasePage{
	private WebDriver driver;

	public AddNewCustomersPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectCustomerRoleInDropdown(String itemDropdown) {
		selectItemInCustomDropdown(driver, AddNewCustomersPageUI.PARENT_DROPDOWN, AddNewCustomersPageUI.CHILD_ITEM_IN_DROPDOWN, itemDropdown);
		
	}
	
	public void enterToAdminCommentTextArea(String value) {
		waitForElementVisible(driver, AddNewCustomersPageUI.ADMIN_COMMENT_TEXTAREA_);
		senkeyToElement(driver, AddNewCustomersPageUI.ADMIN_COMMENT_TEXTAREA_, value);
	}
	



	


	

	


	
	

}

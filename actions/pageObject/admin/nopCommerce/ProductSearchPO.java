package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;



public class ProductSearchPO extends BasePage{
	private WebDriver driver;

	public ProductSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMessageInTableDisplayed() {
		waitForElementVisible(driver, ProductSearchPageUI.TABLE_MESSAGE);
		return isElementDisplayed(driver, ProductSearchPageUI.TABLE_MESSAGE);
	}

	


	

	


	
	

}

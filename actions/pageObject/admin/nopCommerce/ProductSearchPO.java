package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductDetailsPageUI;
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

	public void clickToExpandPanelSearch() {
		//1 - get tag i attibute
		waitForElementVisible(driver,ProductSearchPageUI.PANEL_SEARCH);
		String toogleIconStatus = getElementAttribute(driver, ProductSearchPageUI.PANEL_SEARCH, "class");
		//2 - if not contains (fa-plus)
		if (toogleIconStatus.contains("down")) {
			waitForElementClickable(driver, ProductSearchPageUI.PANEL_SEARCH);
			clickToElement(driver, ProductSearchPageUI.PANEL_SEARCH);
		}
	}
	


	

	


	
	

}

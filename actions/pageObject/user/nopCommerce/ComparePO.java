package pageObject.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
//import pageObjects.user.nopCommerce.HomePageObject;
//import pageObjects.user.nopCommerce.SearchPageObject;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.ComparePageUI;
import pageUIs.nopCommerce.RegisterPageUI;
import pageUIs.nopCommerce.WishlistPageUI;

public class ComparePO extends BasePage{
	private WebDriver driver;

	public ComparePO(WebDriver driver) {
		this.driver = driver;
	}


	public void clickToClearListButton() {
		waitForElementClickable(driver, ComparePageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, ComparePageUI.CLEAR_LIST_BUTTON);
		
	}
	
	public boolean isRowProductItemDisplayedByText(String headerName, String productItem1, String productItem2) {
		waitForElementInvisible(driver, ComparePageUI.ROW_PRODUCT_ITEM_BY_TEXT, headerName, productItem1, productItem2);
		return isElementUnDisplayed(driver, ComparePageUI.ROW_PRODUCT_ITEM_BY_TEXT, headerName, productItem1, productItem2);
	}



	
	

}

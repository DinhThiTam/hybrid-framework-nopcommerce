package pageObject.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HomePageUI;
import pageUIs.nopCommerce.SearchPageUI;


public class SearchPO extends BasePage{
	private WebDriver driver;

	public SearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSearchMessageDisplayedByText(String messageText) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_MESSAGE_BY_TEXT, messageText);
		return isElementDisplayed(driver, SearchPageUI.SEARCH_MESSAGE_BY_TEXT, messageText);
	}
	
	public boolean isProductDisplayedByTitle(String productTitle) {
		waitForElementVisible(driver, SearchPageUI.PRODUCT_TITLE_LINK, productTitle);
		return isElementDisplayed(driver, SearchPageUI.PRODUCT_TITLE_LINK, productTitle);
	}

	

	

	


}

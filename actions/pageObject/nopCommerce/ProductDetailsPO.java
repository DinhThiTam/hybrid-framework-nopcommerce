package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.ProductDetailsPageUI;

public class ProductDetailsPO extends BasePage{
	private WebDriver driver;

	public ProductDetailsPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getMessageInProductDetailsDisplayedByText() {
		waitForElementVisible(driver, ProductDetailsPageUI.MESSAGE_BY_TEXT);
		return getElementText(driver, ProductDetailsPageUI.MESSAGE_BY_TEXT);
	}
	

	public void clickButtonByID(String buttonID) {
		scrollToElement(driver, ProductDetailsPageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, ProductDetailsPageUI.BUTTON_BY_ID, buttonID);
		
	}
	public void clickToCloseIconInMessage() {
		waitForElementClickable(driver, ProductDetailsPageUI.CLOSE_ICON_IN_MESSAGE);
		clickToElement(driver, ProductDetailsPageUI.CLOSE_ICON_IN_MESSAGE);
	}


	






	


}

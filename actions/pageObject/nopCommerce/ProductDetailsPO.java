package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.HomePageUI;
import pageUIs.nopCommerce.MyAccountUI;
import pageUIs.nopCommerce.ProductDetailsPageUI;
import pageUIs.nopCommerce.ProductReviewPageUI;

public class ProductDetailsPO extends BasePage{
	private WebDriver driver;

	public ProductDetailsPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getMessageInProductDetailsDisplayedByText(String messageText) {
		waitForElementVisible(driver, ProductDetailsPageUI.MESSAGE_BY_TEXT, messageText);
		return getElementText(driver, ProductDetailsPageUI.MESSAGE_BY_TEXT, messageText);
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

package pageObject.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HomePageUI;
import pageUIs.nopCommerce.MyAccountUI;
import pageUIs.nopCommerce.ProductReviewPageUI;

public class ProductReviewPO extends BasePage{
	private WebDriver driver;

	public ProductReviewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToReviewTextInTextArea(String value) {
		waitForElementVisible(driver, ProductReviewPageUI.TEXT_REVIEW_TEXTAREA);
		senkeyToElement(driver, ProductReviewPageUI.TEXT_REVIEW_TEXTAREA, value);
	}
	
	public String getAddReviewSuccessMessage() {
		waitForElementVisible(driver, ProductReviewPageUI.REVIEW_SUCCESS_MESSAGE);
		return getElementText(driver, ProductReviewPageUI.REVIEW_SUCCESS_MESSAGE);
	}






	


}

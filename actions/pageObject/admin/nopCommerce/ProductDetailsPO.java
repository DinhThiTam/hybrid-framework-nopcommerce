package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.ProductDetailsPageUI;
import pageUIs.nopCommerce.LoginPageUI;


public class ProductDetailsPO extends BasePage{
	private WebDriver driver;

	public ProductDetailsPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFormTitleProductDetailsDisplayed() {
		waitForElementVisible(driver, ProductDetailsPageUI.FORM_TITLE);
		return isElementDisplayed(driver, ProductDetailsPageUI.FORM_TITLE);
	}
	
	

}

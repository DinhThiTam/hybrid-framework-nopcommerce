package pageObject.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
//import pageObjects.user.nopCommerce.HomePageObject;
//import pageObjects.user.nopCommerce.SearchPageObject;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.CheckoutPageUI;
import pageUIs.nopCommerce.ComparePageUI;
import pageUIs.nopCommerce.RegisterPageUI;
import pageUIs.nopCommerce.WishlistPageUI;

public class CheckoutPO extends BasePage{
	private WebDriver driver;

	public CheckoutPO(WebDriver driver) {
		this.driver = driver;
	}

public void clickToButtonInCheckoutPageByTitleAndName(String stepTitle, String buttonName) {
	waitForElementClickable(driver, CheckoutPageUI.BUTTON_IN_CHECKOUTPAGE_BY_TITLE_AND_NAME_, stepTitle, buttonName);
	clickToElement(driver, CheckoutPageUI.BUTTON_IN_CHECKOUTPAGE_BY_TITLE_AND_NAME_, stepTitle, buttonName);
}




	
	

}

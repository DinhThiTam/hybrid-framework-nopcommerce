package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.DesktopsPageUI;
import pageUIs.nopCommerce.ProductDetailsPageUI;

public class ProductDetailsPO extends BasePage{
	private WebDriver driver;

	public ProductDetailsPO(WebDriver driver) {
		this.driver = driver;
	}


	public void clickButtonByID(String buttonID) {
		scrollToElement(driver, ProductDetailsPageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, ProductDetailsPageUI.BUTTON_BY_ID, buttonID);
		
	}
	
	public void hoverToShoppingCartHeaderMenu() {
		scrollToElement(driver, ProductDetailsPageUI.SHOPPING_CART_HEADER_MENU);
		hoverToElement(driver, ProductDetailsPageUI.SHOPPING_CART_HEADER_MENU);
		
	}

	public boolean isProductInfoInMiniShoppingCartHeaderByClass(String infoText) {
		waitForElementVisible(driver, ProductDetailsPageUI.MINI_SHOPPING_CART_BY_TEXT, infoText);
		return isElementDisplayed(driver, ProductDetailsPageUI.MINI_SHOPPING_CART_BY_TEXT, infoText);
	}


	public float getPriceUnit() {
		String unitPrice = getElementText(driver, ProductDetailsPageUI.UNIT_PRICE).replaceAll(",", "");
		float unitPriceCast = Float.parseFloat(unitPrice);
		System.out.println(unitPriceCast);
		return unitPriceCast;
	}




	






	


}

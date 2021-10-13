package pageObject.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
//import pageObjects.user.nopCommerce.HomePageObject;
//import pageObjects.user.nopCommerce.SearchPageObject;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.ComparePageUI;
import pageUIs.nopCommerce.RegisterPageUI;
import pageUIs.nopCommerce.ShoppingCartUI;
import pageUIs.nopCommerce.WishlistPageUI;

public class ShoppingCartPO extends BasePage{
	private WebDriver driver;

	public ShoppingCartPO(WebDriver driver) {
		this.driver = driver;
	}

	public ProductDetailsPO clickToEditButtonInTableByRowNumber(String rowNumber) {
		waitForElementClickable(driver, ShoppingCartUI.EDIT_BUTTON_IN_TABLE_BY_ROW_NUMBER, rowNumber);
		clickToElement(driver, ShoppingCartUI.EDIT_BUTTON_IN_TABLE_BY_ROW_NUMBER, rowNumber);
		return new ProductDetailsPO(driver);
	}
	
	public void clickToRemoveIconInTableByRowValue(String sku, String product, String price, String qty) {
		waitForElementClickable(driver, ShoppingCartUI.ROW_VALUE_BY_SKU_PRODUCT_PRICE_QTY_TOTAL, sku, product,price, qty);
		clickToElement(driver, ShoppingCartUI.ROW_VALUE_BY_SKU_PRODUCT_PRICE_QTY_TOTAL, sku, product,price, qty);
	}
	
	public boolean isValueInTableUnDisplayed(String sku, String product, String price, String qty) {
		waitForElementInvisible(driver, ShoppingCartUI.ROW_VALUE_BY_SKU_PRODUCT_PRICE_QTY_TOTAL, sku, product,price, qty);
		return isElementUnDisplayed(driver, ShoppingCartUI.ROW_VALUE_BY_SKU_PRODUCT_PRICE_QTY_TOTAL, sku, product,price, qty);
	}

	public boolean isCartEmptyMessageDisplayed() {
		waitForElementVisible(driver, ShoppingCartUI.MESSAGE_CART_EMPTY);
		return isElementDisplayed(driver, ShoppingCartUI.MESSAGE_CART_EMPTY);
	}



	
	

}

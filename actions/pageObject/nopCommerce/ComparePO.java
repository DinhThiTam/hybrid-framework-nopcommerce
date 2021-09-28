package pageObject.nopCommerce;

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

	public void clickToLinkForShare() {
		waitForElementClickable(driver, WishlistPageUI.LINK_FOR_SHARE);
		clickToElement(driver, WishlistPageUI.LINK_FOR_SHARE);
		
	}

	public boolean isPageTitleDisplayedByText(String firstName, String lastName) {
		waitForElementVisible(driver, WishlistPageUI.PAGE_TITLE_BY_TEXT, firstName, lastName);
		return isElementDisplayed(driver, WishlistPageUI.PAGE_TITLE_BY_TEXT, firstName, lastName);
	}

	public void clickToPageActionByRowAndClass(String rowNumber, String tdClass) {
		waitForElementClickable(driver, WishlistPageUI.ACTION_WISHLIST_PAGE_BY_ROW_AND_CLASS, rowNumber,tdClass);
		checkTheCheckboxOrRadio(driver, WishlistPageUI.ACTION_WISHLIST_PAGE_BY_ROW_AND_CLASS, rowNumber,tdClass);
		
	}

	public boolean isPageTitleShoppingCartDisplayed() {
		waitForElementVisible(driver, WishlistPageUI.PAGE_TITLE_SHOPPING_CART);
		return isElementDisplayed(driver, WishlistPageUI.PAGE_TITLE_SHOPPING_CART);
	}

	public boolean isPageMessageNoDataDisplayedByText(String messageText) {
		waitForElementVisible(driver, WishlistPageUI.NO_DATA_PAGE_BY_TEXT, messageText);
		return isElementDisplayed(driver, WishlistPageUI.NO_DATA_PAGE_BY_TEXT, messageText);
	}

	public boolean isValueInTableUnDisplayed(String rowNumber,String addToCartClass, String skuClass, String imageClass,String productClass, String priceClass, String qtyClass, String totalClass, String removeClass) {
		waitForElementInvisible(driver, WishlistPageUI.VALUE_IN_ROW_BY_CLASS, rowNumber,addToCartClass,skuClass,imageClass,productClass,priceClass,qtyClass,totalClass,removeClass);
		return isElementUnDisplayed(driver, WishlistPageUI.VALUE_IN_ROW_BY_CLASS, rowNumber,addToCartClass,skuClass,imageClass,productClass,priceClass,qtyClass,totalClass,removeClass);
	}

	public void clickToClearListButton() {
		waitForElementClickable(driver, ComparePageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, ComparePageUI.CLEAR_LIST_BUTTON);
		
	}


	
	

}

package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HomePageUI;
import pageUIs.nopCommerce.MyAccountUI;




public class MyAccountPO extends BasePage{
	private WebDriver driver;

	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
	}

	public void openTabMenuByName(String tabMenuName) {
		waitForElementClickable(driver, MyAccountUI.TAB_MENU_BY_NAME, tabMenuName);
		clickToElement(driver, MyAccountUI.TAB_MENU_BY_NAME, tabMenuName);
		
	}

	public String getTextboxValueByClass(String textClass) {
		waitForElementVisible(driver, MyAccountUI.VALUE_TEXT_BY_CLASS, textClass);
		return getElementText(driver, MyAccountUI.VALUE_TEXT_BY_CLASS, textClass);
	}
	
	public boolean isChangePasswordSuccessMessageDisplayed() {
		waitForElementVisible(driver, MyAccountUI.CHANGE_PASSWORD_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, MyAccountUI.CHANGE_PASSWORD_SUCCESS_MESSAGE);
	}
	
	public void clickToCloseMessageButton() {
		waitForElementClickable(driver, MyAccountUI.CLOSE_MESSAGE_BUTTON);
		clickToElement(driver, MyAccountUI.CLOSE_MESSAGE_BUTTON);
	}

	public String getReviewTextByClass(String reviewClass) {
		waitForElementVisible(driver, MyAccountUI.REVIEW_TEXT_BY_CLASS, reviewClass);
		return getElementText(driver, MyAccountUI.REVIEW_TEXT_BY_CLASS, reviewClass);
	}





	


}

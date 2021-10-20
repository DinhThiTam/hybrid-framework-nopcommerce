package pageObject.user.nopCommerce;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.MyAccountUI;




public class MyAccountPO extends BasePage{
	private WebDriver driver;

	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
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
	
	public boolean isReviewTextDisplayedByClassAndText(String reviewClass, String textReview) {
		waitForElementVisible(driver, MyAccountUI.REVIEW_TEXT_BY_CLASS, reviewClass, textReview);
		return isElementDisplayed(driver, MyAccountUI.REVIEW_TEXT_BY_CLASS, reviewClass, textReview);
	}
	
	public void openTabMenuAddress() {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("document.querySelector(\"li[class^='customer-addresses'] > a\").click()");
	}
	
	public void openTabMenuChangePasswod() {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("document.querySelector(\"li[class^='change-password'] > a\").click()");
	}
	
	public void openTabMenuProductReview() {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("document.querySelector(\"li[class^='customer-reviews'] > a\").click()");
	}
	
	public void openTabMenuCustomerInfo() {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("document.querySelector(\"li[class^='customer-info'] > a\").click()");
	}
	





	
	private JavascriptExecutor jsExecutor;

}

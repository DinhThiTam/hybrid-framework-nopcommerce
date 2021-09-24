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

	



	


}

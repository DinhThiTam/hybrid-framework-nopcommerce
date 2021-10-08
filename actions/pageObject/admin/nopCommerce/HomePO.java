package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HomePageUI;


public class HomePO extends BasePage{
	private WebDriver driver;

	public HomePO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHomePageSliderDisplayed() {
		waitForElementVisible(driver, HomePageUI.HOMEPAGE_SLIDER);
		return isElementDisplayed(driver, HomePageUI.HOMEPAGE_SLIDER);
	}
	
	public boolean isLogoutLinkDisplay() {
		waitForElementVisible(driver, HomePageUI.LOGOUT_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGOUT_LINK);
	}

	
	


}

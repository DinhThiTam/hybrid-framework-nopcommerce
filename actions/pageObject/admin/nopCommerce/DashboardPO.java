package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.admin.nopCommerce.DashboardPageUI;



public class DashboardPO extends BasePage{
	private WebDriver driver;

	public DashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardPageContentDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.DASHBOARDPAGE_CONTENT);
		return isElementDisplayed(driver, DashboardPageUI.DASHBOARDPAGE_CONTENT);
	}

	

	


	
	

}

package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	WebDriver driver;

	private PageGenerator() {
	}

	public static LoginPO getLoginPage(WebDriver driver) {

		return new LoginPO(driver);
	}

	public static DashboardPO getDashboardPage(WebDriver driver) {

		return new DashboardPO(driver);
	}
	
	public static ProductSearchPO getProductSearchPage(WebDriver driver) {

		return new ProductSearchPO(driver);
	}



}

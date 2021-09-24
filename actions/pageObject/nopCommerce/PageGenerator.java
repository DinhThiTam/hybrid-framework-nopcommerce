package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;


public class PageGenerator {
	WebDriver driver;

	private PageGenerator() {
	}
	
	public static LoginPO getLoginPage(WebDriver driver) {

		return new LoginPO(driver);
	}
	
	public static HomePO getHomePage(WebDriver driver) {

		return new HomePO(driver);
	}
	
	public static RegisterPO getRegisterPage(WebDriver driver) {

		return new RegisterPO(driver);
	}
	
	public static MyAccountPO getMyAccountPage(WebDriver driver) {

		return new MyAccountPO(driver);
	}
}

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
	
	public static ProductDetailsPO getProductDetailsPage(WebDriver driver) {

		return new ProductDetailsPO(driver);
	}
	
	public static CustomersSearchPO getCustomersSearchPage(WebDriver driver) {

		return new CustomersSearchPO(driver);
	}
	
	public static AddNewCustomersPO getAddNewCustomersPage(WebDriver driver) {

		return new AddNewCustomersPO(driver);
	}
	
	public static CustomerDetailsPO getCustomerDetailsPage(WebDriver driver) {

		return new CustomerDetailsPO(driver);
	}
	
	public static AddNewAddressPO getAddNewAddressPage(WebDriver driver) {

		return new AddNewAddressPO(driver);
	}





}

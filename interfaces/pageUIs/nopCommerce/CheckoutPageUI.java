package pageUIs.nopCommerce;

public class CheckoutPageUI {
	public static final String BUTTON_IN_CHECKOUTPAGE_BY_TITLE_AND_NAME_ = "//div[contains(string(),'%s')]/following-sibling::div//div[contains(string(),'%s')]";
	public static final String TEXT_PAYMENT_ = "//tbody/tr/td/p[%s]";
	public static final String ORDER_NUMER = "//div[@class='order-number']/strong";
	public static final String TEXT_BY_TITLE_AND_CLASS = "//strong[text()='%s']/parent::div/following-sibling::ul/li[@class='%s']";
}
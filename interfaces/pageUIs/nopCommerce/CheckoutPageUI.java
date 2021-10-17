package pageUIs.nopCommerce;

public class CheckoutPageUI {
	public static final String BUTTON_IN_CHECKOUTPAGE_BY_TITLE_AND_NAME_ = "//div[contains(string(),'%s')]/following-sibling::div//div[contains(string(),'%s')]";
	public static final String TEXT_PAYMENT_ = "//tbody/tr/td/p[%s]";
	
	public static final String MESSAGE_SUCCESS_ORDER = "//h1[text()='Thank you']/parent::div/following-sibling::div//strong[text()='Your order has been successfully processed!']";

}
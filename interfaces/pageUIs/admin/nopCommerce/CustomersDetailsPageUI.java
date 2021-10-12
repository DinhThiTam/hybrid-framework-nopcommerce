package pageUIs.admin.nopCommerce;

public class CustomersDetailsPageUI {
	public static final String ADD_CUSTOMER_SUCCESS_MESSAGE= "//div[@class='content-wrapper']//div[contains(string(),'The new customer has been added successfully.')]";

	public static final String ROW_VALUE_BY_FIRSTNAME_LASTNAME_EMAIL_PHONE_FAX_ADDRESS = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[string()='%s']//following-sibling::td[contains(string(),'%s')]";
	
	public static final String BUTTON_IN_TABLE_BY_CART_TITLE_AND_BUTTONNAME = "//div[contains(string(),'%s')]/parent::div/following-sibling::div//a[contains(string(),'%s')]";
	
}

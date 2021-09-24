package pageUIs.nopCommerce;

public class BasePageUI {
	public static final String MENU_HEADER_BY_CLASS = "//div[@class='header']//a[@class='%s']";
	public static final String TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String BUTTON_BY_NAME = "//button[text()='%s']";
	public static final String RADIO_BUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String MENU_BY_MENU_POSITION_AND_MENU_NAME = "//ul[@class='%s']//a[text()='%s']";
	

}

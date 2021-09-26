package pageUIs.nopCommerce;

public class BasePageUI {
	public static final String MENU_HEADER_BY_CLASS = "//div[@class='header']//a[@class='%s']";
	public static final String TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String BUTTON_BY_NAME = "//div[@class='page-body']//button[text()='%s']";
	public static final String RADIO_AND_CHECKBOX_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String MENU_BY_MENU_POSITION_AND_MENU_NAME = "//ul[@class='%s']//a[text()='%s']";
	public static final String MENU_FOOTER_BY_NAME = "//div[@class='footer']//a[text()='%s']";
	public static final String PRODUCT_TITLE_SIZE= "//h2[@class='product-title']";
	public static final String PRODUCT_TITLE_BY_TEXT = "//a[text()='%s']";
	public static final String TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//a[text()='%s']";
	public static final String TABLE_HEADER_BY_ID_AND_NAME = "//table[@id='%s']//th[string()='%s']//preceding-sibling::th";
}

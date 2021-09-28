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
	public static final String TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//table[@class='%s']//tbody/tr[%s]/td[%s]";
	public static final String TABLE_HEADER_HORIZONTAL_BY_CLASS_AND_NAME = "//table[@class='%s']//tr/th[text()='%s']/preceding-sibling::th";
	public static final String TABLE_HEADER_VERTICAL_BY_CLASS_AND_NAME = "//table[@class='%s']//tr[@class='%s']//preceding-sibling::tr";
	public static final String BUTTON_ADD_TO_SOMETHING_BY_PRODUCT_TITLE_AND_BUTTON_TITLE = "//a[text()='%s']/parent::h2[@class='product-title']//following-sibling::div[@class='add-info']//button[@title='%s']";
	public static final String MESSAGE_BY_TEXT = "//p[@class='content']";
	public static final String CLOSE_ICON_IN_MESSAGE = "//span[@title='Close']";
}

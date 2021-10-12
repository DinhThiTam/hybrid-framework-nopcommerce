package pageUIs.admin.nopCommerce;

public class AdminBasePageUI {
	public static final String MENU_LINK_BY_NAME = "//ul[@role='menu']/li/a/p[contains(text(),'%s')]";
	public static final String TEXTBOX_BY_ID_AT_ADMIN_SITE = "//input[@id='%s']";
	public static final String SUB_MENU_LINK_BY_NAME = "//ul[@class='nav nav-treeview' and @style]//p[contains(text(),' %s')]";
	public static final String BUTTON_BY_ID = "//button[@id='%s']";
	public static final String DATATABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//div[@class='%s']//tbody/tr[%s]/td[%s]";
	public static final String TABLE_BY_DATATABLE_AND_HEADERNAME = "//div[@class='%s']//tr/th[text()='%s']/preceding-sibling::th";
	public static final String CHECKBOX_OR_RADIO_AT_ADMINSITE= "//input[@id='%s']";
	public static final String DROPDOWN_BY_NAME_AT_ADMINSITE = "//select[@name='%s']";
	public static final String BUTTON_IN_HEADER_ON_SUBMENU__PAGE = "//h1[contains(string(),'%s')]/following-sibling::div/*[contains(string(),'%s')]";
	public static final String TOOGLE_ICON_BY_CARD_NAME = "//div[@class='card-title' and contains(string(),'%s')]/following-sibling::div//i";
	public static final String DYNAMIC_DROPDOWN_BY_LABEL= "//label[text()='%s']/parent::div/parent::div/following-sibling::div//div[@role='listbox']";
	public static final String ITEM_DROPDOWN_BY_TEXT = "//li[text()='%s']";
	public static final String ITEM_SELECTED = "//ul[@id='SelectedCustomerRoleIds_taglist']";
	public static final String DELETE_ITEM_ON_CUSTOMER_ROLE = "//span[@title='delete']";
	public static final String ROW_VALUE_BY_EMAIL_NAME_CUSTOMERROLES_COMPANYNAME = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[string()='']/following-sibling::td[contains(string(),'Edit')]";
	public static final String ADMIN_COMMENT_TEXTAREA_ = "//textarea[@id='AdminComment']";
	public static final String BUTTON_BY_NAME_ATTRIBUTE = "//button[@name='%s']";
	public static final String SUCCESS_MESSAGE_BY_TEXT= "//div[@class='content-wrapper']//div[contains(string(),'%s')]";
	public static final String BUTTON_BY_CONTAINS_TEXT= "//button[contains(string(),'%s')]";
	public static final String BACK_BUTTON_BY_NAME= "//small[contains(string(),'%s')]";
	
	
}


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
	
	
	
}


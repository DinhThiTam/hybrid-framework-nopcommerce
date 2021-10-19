package pageUIs.nopCommerce;

public class ShoppingCartUI {
	public static final String INPUT_CLASS_BUTTON = "//input[@class='qty-input']";
	public static final String EDIT_BUTTON_IN_TABLE_BY_ROW_NUMBER = "//table[@class='cart']//tr[%s]//a[text()='Edit']";
	public static final String MESSAGE_CART_EMPTY = "//div[@class='page shopping-cart-page']/div[contains(string(),'Your Shopping Cart is empty!')]";
	public static final String ROW_VALUE_BY_SKU_PRODUCT_PRICE_QTY_TOTAL_REMOVE = "//td[contains(string(),'%s')]/following-sibling::td[contains(string(),'%s')]/following-sibling::td[contains(string(),'%s')]/following-sibling::td/input[@value='%s']/parent::td/following-sibling::td[contains(string(),'%s')]/following-sibling::td[@class='remove-from-cart']";
	

}

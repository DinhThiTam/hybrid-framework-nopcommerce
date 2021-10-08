package pageUIs.admin.nopCommerce;

public class LoginPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String LOGIN_BUTTON = "//div/button[@type='submit']";
	public static final String LOGIN_EMAIL_EMPTY_MESSAGE = "//span[@id='Email-error' and text()='Please enter your email']";
	public static final String LOGIN_EMAIL_INVALID_MESSAGE = "//span[@id='Email-error' and text()='Wrong email']";
	public static final String LOGIN_EMAIL_NOT_REGISTER_MESSAGE = "//div[text()='Login was unsuccessful. Please correct the errors and try again.']//li[text()='No customer account found']";
	public static final String LOGIN_PASSWORD_EMPTY_MESSAGE = "//div[text()='Login was unsuccessful. Please correct the errors and try again.']//li[text()='The credentials provided are incorrect']";
	public static final String LOGIN_PASSWORD_INVALID_MESSAGE = "//div[text()='Login was unsuccessful. Please correct the errors and try again.']//li[text()='The credentials provided are incorrect']";
	public static final String HOMEPAGE_IMAGE = "//img[@alt='nopCommerce demo store']";
	public static final String ADMIN_LOGINPAGE_TITLE = "//div[@class='title']";
}

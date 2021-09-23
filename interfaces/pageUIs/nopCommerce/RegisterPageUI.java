package pageUIs.nopCommerce;

public class RegisterPageUI {
	public static final String GENDER_MALE_RADIO = "//input[@id='gender-male']";
	public static final String FIRSTNAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRMPASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	public static final String SUCCESS_MESSAGE = "//div[@class='result' and text()='Your registration completed']";
	public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
	public static final String EMAIL_INVALID_MESSAGE = "//div[@class='page-body']//li[text()='Wrong email']";
	public static final String EMAIL_EXIST_MESSAGE = "//div[@class='page-body']//li[text()='The specified email already exists']";
	public static final String PASSWORD_INVALID_MESSAGE = "//span[@id='Password-error']/p[text()='Password must meet the following rules: ']";
	public static final String CONFIRM_PASSWORD_INVALID_MESSAGE = "//span[@id='ConfirmPassword-error' and text() = 'The password and confirmation password do not match.']";	
	public static final String FIRSTNAME_EMPTY_MESSAGE = "//span[@id='FirstName-error' and text()='First name is required.']";	
	public static final String LASTNAME_EMPTY_MESSAGE = "//span[@id='LastName-error' and text()='Last name is required.']";	
	public static final String PASSWORD_EMPTY_MESSAGE = "//span[@id='Password-error' and text() = 'Password is required.']";
	public static final String CONFIRM_PASSWORD_EMPTY_MESSAGE = "//span[@id='ConfirmPassword-error' and text() = 'Password is required.']";
	public static final String EMAIL_EMPTY_MESSAGE = "//span[@id='Email-error' and text() = 'Email is required.']";	
	public static final String GENDER_FEMALE_RADIO_BUTTON = "//input[@id='gender-female']";	
	public static final String DATE_OF_BIRTHDAY_DROPDOWN = "//select[@name='DateOfBirthDay']";	
	public static final String DATE_OF_BIRTHMONTH_DROPDOWN = "//select[@name='DateOfBirthMonth']";	
	public static final String DATE_OF_BIRTHYEAR_DROPDOWN = "//select[@name='DateOfBirthYear']";	
	public static final String COMPANY_NAME_TEXTBOX = "//input[@id='Company']";	
	public static final String SAVE_BUTTON = "//button[@id='save-info-button']";	
	public static final String SEARCH_LINK = "//ul[@class='list']//a[text()='Search']";	
	public static final String DYNAMIC_DROPDOWN = "//select[@name='%s']";
}

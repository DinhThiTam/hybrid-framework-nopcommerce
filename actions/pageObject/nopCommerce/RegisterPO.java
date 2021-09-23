package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
//import pageObjects.user.nopCommerce.HomePageObject;
//import pageObjects.user.nopCommerce.SearchPageObject;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.RegisterPageUI;

public class RegisterPO extends BasePage{
	private WebDriver driver;

	public RegisterPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToGenderRadioButton() {
		waitForElementClickable(driver, RegisterPageUI.GENDER_MALE_RADIO);
		clickToElement(driver, RegisterPageUI.GENDER_MALE_RADIO);
	}
	
	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
		
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);	
	}
	
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPassword);	
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);		
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}

//	public HomePageObject clickToLogoutLink() {
//		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
//		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);	
//		return new HomePageObject(driver);
//	}
	
	public boolean isEmailInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_INVALID_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_INVALID_MESSAGE);
	}
	
	public boolean isEmailExistMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_EXIST_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_EXIST_MESSAGE);
	}

	public boolean isEmailEmptyMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_EMPTY_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_EMPTY_MESSAGE);
	}
	
	public boolean isPasswordInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_INVALID_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.PASSWORD_INVALID_MESSAGE);
	}
	
	public boolean isConfirmPasswordInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_INVALID_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.CONFIRM_PASSWORD_INVALID_MESSAGE);
	}
	
	public boolean isFirstnameEmptyInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_EMPTY_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.FIRSTNAME_EMPTY_MESSAGE);
	}
	
	public boolean isLastnameEmptyInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_EMPTY_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.LASTNAME_EMPTY_MESSAGE);
	}
	
	public boolean isPasswordEmptyInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_EMPTY_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.PASSWORD_EMPTY_MESSAGE);
	}
	
	public boolean isConfirmPasswordEmptyInvalidMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_EMPTY_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.CONFIRM_PASSWORD_EMPTY_MESSAGE);
	}
	
	public void registerToSystem(String emailAddress, String password, String confirmPassword, String firstName, String lastName ) {
		clickToGenderRadioButton();
		enterToFirstNameTextbox(firstName);
		enterToLastNameTextbox(lastName);
		enterToEmailTextbox(emailAddress);
		enterToPasswordTextbox(password);
		enterToConfirmPasswordTextbox(confirmPassword);
		clickToRegisterButton();
	}
	public String getCompanyNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX);
		return getElementAttribute(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX, "value");
	}
	public String getEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		return getElementAttribute(driver, RegisterPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getLastNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		return getElementAttribute(driver, RegisterPageUI.LASTNAME_TEXTBOX, "value");
	}
	
	public String getFirstNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		return getElementAttribute(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, "value");
	}
	
	public void enterToCompanyNameTextbox(String companyName) {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX, companyName );		
	}
	public void clickToSaveButton() {
		waitForElementClickable(driver, RegisterPageUI.SAVE_BUTTON);
		clickToElement(driver, RegisterPageUI.SAVE_BUTTON);
		
	}

	public void clearFirstNameTextbox() {
		waitForElementInvisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		clearTextbox(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);	
	}
	
	public void clearLastNameTextbox() {
		waitForElementInvisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		clearTextbox(driver, RegisterPageUI.LASTNAME_TEXTBOX);	
	}
	
	public void clearEmailTextbox() {
		waitForElementInvisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		clearTextbox(driver, RegisterPageUI.EMAIL_TEXTBOX);	
	}
	
	public void clearCompanyNameTextbox() {
		waitForElementInvisible(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX);
		clearTextbox(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX);	
	}
	
	public void clearAllTextbox() {
		clearFirstNameTextbox();
		clearLastNameTextbox();
		clearEmailTextbox();
		clearCompanyNameTextbox();
	}
	public void clickToGenderFemaleRadioButton() {
		waitForElementClickable(driver, RegisterPageUI.GENDER_FEMALE_RADIO_BUTTON);
		clickToElement(driver, RegisterPageUI.GENDER_FEMALE_RADIO_BUTTON);
		
	}
	public String getSelectedBirthmonthDropdown() {
		return getSelectedItemDropdown(driver, RegisterPageUI.DATE_OF_BIRTHMONTH_DROPDOWN);
	}
	public String getSelectedBirthdayDropdown() {
		return getSelectedItemDropdown(driver, RegisterPageUI.DATE_OF_BIRTHDAY_DROPDOWN);
	}
	public String getSelectedBirthyearDropdown() {
		return getSelectedItemDropdown(driver, RegisterPageUI.DATE_OF_BIRTHYEAR_DROPDOWN);
	}
	public void selectToBirthdayDropDown(String itemText) {
		selectDropdownByText(driver, RegisterPageUI.DATE_OF_BIRTHDAY_DROPDOWN, itemText);	
	}
	
	public void selectToBirthmonthDropDown(String itemText) {
		selectDropdownByText(driver, RegisterPageUI.DATE_OF_BIRTHMONTH_DROPDOWN, itemText);	
	}
	
	public void selectToBirthyearDropDown(String itemText) {
		selectDropdownByText(driver, RegisterPageUI.DATE_OF_BIRTHYEAR_DROPDOWN, itemText);		
	}
	
	public void customerInfo(String firstName, String lastName, String birthDay, String birthMonth, String birthYear, String emailAddress, String companyName) {
		clickToGenderFemaleRadioButton();
		//enterToFirstNameTextbox(firstName);
		enterToLastNameTextbox(lastName);
		selectToBirthdayDropDown(birthDay);
		selectToBirthmonthDropDown(birthMonth);
		selectToBirthyearDropDown(birthYear);
		enterToEmailTextbox(emailAddress);
		enterToCompanyNameTextbox(companyName);
		clickToSaveButton();	
	}
//	public SearchPageObject clickToSearchFooterLink() {
//		waitForElementClickable(driver, RegisterPageUI.SEARCH_LINK);
//		clickToElement(driver, RegisterPageUI.SEARCH_LINK);
//		return new SearchPageObject(driver);
//	}
	public void enterToDropdownByName(WebDriver driver, String itemText, String dropdownName) {
		selectDropdownByText(driver, RegisterPageUI.DYNAMIC_DROPDOWN, itemText, dropdownName);
	}


	

	
	

}

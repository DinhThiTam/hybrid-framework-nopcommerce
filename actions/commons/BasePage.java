package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.admin.nopCommerce.AdminBasePageUI;

import pageUIs.nopCommerce.BasePageUI;




/**
 * @author
 *
 */
public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();

	}

	public String getPageSouce(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void clearTextbox (WebDriver driver, String locator) {
		getElement(driver, locator).clear();
	}

	public Alert waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.accept();
		sleepInsecond(2);
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		return alert.getText();
	}

	public void senkeyToAlert(WebDriver driver, String value) {
		alert = waitAlertPresence(driver);
		alert.sendKeys(value);
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {

			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle().trim();
			if (actualTitle.equals(pageTitle)) {
				break;
			}
		}
	}

	public void closeAlltabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
	}

	public void sleepInsecond(long shortTimeoutInsecond) {
		try {
			Thread.sleep(shortTimeoutInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}
	
	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}

	public void senkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	
	public void senkeyToElement(WebDriver driver, String locator, String value, String...params) {
		locator = getDynamicLocator(locator, params);
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public int getSizeElements(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}
	
	public int getSizeElements(WebDriver driver, String locator, String...params) {
		return getElements(driver, getDynamicLocator(locator, params)).size();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();	
	}
	
	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}
	
	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public void selectDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public String getSelectedItemDropdown(WebDriver driver, String locator){
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDropdown(WebDriver driver, String locator, String... params){
		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInsecond(1);

		explicitWait = new WebDriverWait(driver, shortTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInsecond(1);

				item.click();
				sleepInsecond(1);
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).getAttribute(attributeName);
	}
	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText().trim();
	}
	
	public String getElementCss(WebDriver driver, String locator, String cssValue) {
		return getElement(driver, locator).getCssValue(cssValue);
	}
	
	public String getElementCss(WebDriver driver, String locator, String cssValue, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).getCssValue(cssValue);
	}
	
	public String convertRgbaToHex(WebDriver driver, String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public void checkTheCheckboxOrRadio(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator, String...params) {
		locator = getDynamicLocator(locator, params);
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			//Displayed: Visible on UI + In DOM
			//Undisplay: Invisible on UI + In DOM
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Undisplayed + not in DOM + Invisible on UI
			return false;
		}
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM and not visible on UI");
			System.out.println("End time " + new Date().toString());
			return true;		
		}else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/dislpayed on UI");
			System.out.println("End time " + new Date().toString());
			return true;
		}else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
		
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator, String...params) {
		locator = getDynamicLocator(locator, params);
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM and not visible on UI");
			System.out.println("End time " + new Date().toString());
			return true;		
		}else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/dislpayed on UI");
			System.out.println("End time " + new Date().toString());
			return true;
		}else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
		
	}
	
	public void overrideGlobalTimeout (WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		
	}
	
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isSelected();
	}
	
	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}
	
	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator, String... params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLocator(locator, params))).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator),getElement(driver, targetLocator)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();	
	}
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String...params) {
		action = new Actions(driver);
		locator = getDynamicLocator(locator, params);
		action.sendKeys(getElement(driver, locator), key).perform();	
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	
	public void scrollToElement(WebDriver driver, String locator, String...params) {
		locator = getDynamicLocator(locator, params);
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));	
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));	
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));	
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));	
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));	
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));	
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));	
	}
	
	//User - Nopcommerce
	public void openMenuHeaderPageByClass(WebDriver driver, String headerClass) {
		waitForElementClickable(driver, BasePageUI.MENU_HEADER_BY_CLASS, headerClass);
		clickToElement(driver, BasePageUI.MENU_HEADER_BY_CLASS, headerClass);
	}
	
	public void enterToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		senkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);	
	}
	
	public void clickToButtonByName(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_NAME, buttonName);
		clickToElement(driver, BasePageUI.BUTTON_BY_NAME, buttonName);
	}
	
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxID);
	}
	
	public void clickToRadioAndCheckboxByLabel(WebDriver driver, String radioLabel) {
		waitForElementClickable(driver, BasePageUI.RADIO_AND_CHECKBOX_BY_LABEL, radioLabel);
		checkTheCheckboxOrRadio(driver, BasePageUI.RADIO_AND_CHECKBOX_BY_LABEL, radioLabel);
	}
	

	public boolean isSelectedItemByLable(WebDriver driver, String itemLabel) {
		return isElementSelected(driver, BasePageUI.RADIO_AND_CHECKBOX_BY_LABEL, itemLabel);
	}
	
	public void selectItemInDropdownByName(WebDriver driver, String value, String dropdownID) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownID);
		selectDropdownByText(driver, BasePageUI.DROPDOWN_BY_NAME, value, dropdownID);
	}
	
	public String getSelectItemInDropdownByName(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownID);
		return getSelectedItemDropdown(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownID);
	}

	public boolean isSelectedItemInRadio(WebDriver driver, String radioLabel) {
		waitForElementVisible(driver, BasePageUI.RADIO_AND_CHECKBOX_BY_LABEL, radioLabel);
		return isElementSelected(driver, BasePageUI.RADIO_AND_CHECKBOX_BY_LABEL, radioLabel);
	}
	
	public boolean isLogoutLinkDisplay(WebDriver driver, String headerClass) {
		waitForElementVisible(driver, BasePageUI.MENU_HEADER_BY_CLASS, headerClass);
		return isElementDisplayed(driver, BasePageUI.MENU_HEADER_BY_CLASS, headerClass);
	}
	
	public void openMenuPage(WebDriver driver, String menuPosition, String menuName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_MENU_POSITION_AND_MENU_NAME, menuPosition, menuName);
		clickToElement(driver, BasePageUI.MENU_BY_MENU_POSITION_AND_MENU_NAME, menuPosition, menuName);
	}
	
	public void openSubMenuPage(WebDriver driver, String menuPosition, String menuName, String subMenuName) {
		waitForElementVisible(driver, BasePageUI.MENU_BY_MENU_POSITION_AND_MENU_NAME, menuPosition, menuName);
		hoverToElement(driver, BasePageUI.MENU_BY_MENU_POSITION_AND_MENU_NAME,menuPosition, menuName);
		
		waitForElementClickable(driver, BasePageUI.MENU_BY_MENU_POSITION_AND_MENU_NAME, menuPosition, subMenuName);
		clickToElement(driver, BasePageUI.MENU_BY_MENU_POSITION_AND_MENU_NAME, menuPosition, subMenuName);
	}
	
	public void openMenuFooterPageByName(WebDriver driver, String footerMenuName) {
		waitForElementClickable(driver, BasePageUI.MENU_FOOTER_BY_NAME, footerMenuName);
		clickToElement(driver, BasePageUI.MENU_FOOTER_BY_NAME, footerMenuName);
	}
	
	public int getProductSize(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_TITLE_SIZE);
		return getSizeElements(driver, BasePageUI.PRODUCT_TITLE_SIZE );
	}
	
	public boolean isProductSizeLessThanOrEqualToThree(WebDriver driver) {
		int size = getSizeElements(driver, BasePageUI.PRODUCT_TITLE_SIZE);
		System.out.println("Size = " + size);
		if (size<=3) {
			return true;	
		}
		return false;
	}
	
	public boolean isProductSizeLessThanOrEqualToNine(WebDriver driver) {
		int size = getSizeElements(driver, BasePageUI.PRODUCT_TITLE_SIZE);
		System.out.println("Size = " + size);
		if (size<=9) {
			return true;	
		}
		return false;
	}
	
	public boolean isProductSizeLessThanOrEqualToSix(WebDriver driver) {
		int size = getSizeElements(driver, BasePageUI.PRODUCT_TITLE_SIZE);
		System.out.println("Size = " + size);
		if (size<=6) {
			return true;	
		}
		return false;
	}
	
//	public void clickToLinkByText(WebDriver driver, String linkText) {
//		waitForElementClickable(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, linkText);
//		clickToElement(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, linkText);
//	}
	
	public boolean isProductDisplayedByTitle(WebDriver driver, String productTitle) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, productTitle);
		return isElementDisplayed(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, productTitle);
	}
	
	public void clickToProductLinkByText(WebDriver driver, String linkText) {
		waitForElementClickable(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, linkText);
		clickToElement(driver, BasePageUI.PRODUCT_TITLE_BY_TEXT, linkText);
	}
	
	public String getValueInTableIDAtColumnHorizontalNameAndRowIndex(WebDriver driver, String tableID, String rowIndex, String headerName) {
		int columnIndex = getSizeElements(driver, BasePageUI.TABLE_HEADER_HORIZONTAL_BY_CLASS_AND_NAME, tableID,headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
	
	public String getValueInTableIDAtColumnVerticalByClassAndRowIndex(WebDriver driver, String tableID, String columnIndex, String headerClass) {
		int rowIndex = getSizeElements(driver, BasePageUI.TABLE_HEADER_VERTICAL_BY_CLASS_AND_NAME, tableID,headerClass) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID,String.valueOf(rowIndex),columnIndex);
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, String.valueOf(rowIndex),columnIndex);
	}
	
	
	
	public String getMessageInProductDetailsDisplayedByText(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.MESSAGE_BY_TEXT);
		return getElementText(driver, BasePageUI.MESSAGE_BY_TEXT);
	}
	
	public void clickToCloseIconInMessage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CLOSE_ICON_IN_MESSAGE);
		clickToElement(driver, BasePageUI.CLOSE_ICON_IN_MESSAGE);
	}
	
	public void clickToButtonAddToSomethingByProductTitleAndButtonTitle(WebDriver driver, String productTitle,String buttonTitle) {
		waitForElementClickable(driver, BasePageUI.BUTTON_ADD_TO_SOMETHING_BY_PRODUCT_TITLE_AND_BUTTON_TITLE, productTitle,buttonTitle);
		clickToElement(driver, BasePageUI.BUTTON_ADD_TO_SOMETHING_BY_PRODUCT_TITLE_AND_BUTTON_TITLE, productTitle,buttonTitle);
	}
	
	public boolean isPageMessageNoDataDisplayedByText(WebDriver driver, String messageText) {
		waitForElementVisible(driver, BasePageUI.NO_DATA_PAGE_BY_TEXT, messageText);
		return isElementDisplayed(driver, BasePageUI.NO_DATA_PAGE_BY_TEXT, messageText);
	}
	
	// Admin - Nopcommerce
//	public void openSubMenuByName(WebDriver driver, String menuName, String subMenuName) {
//		waitForElementClickable(driver,AdminBasePageUI.MENU_BY_NAME, menuName);
//		clickToElement(driver, AdminBasePageUI.MENU_BY_NAME, menuName);
//		
//		waitForElementClickable(driver,AdminBasePageUI.SUBMENU_BY_NAME, subMenuName);
//		clickToElement(driver, AdminBasePageUI.SUBMENU_BY_NAME, subMenuName);
//		
//	}
	
	public void openSubMenuPageByName(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		clickToElement(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		
		waitForElementClickable(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, subMenuPageName);
		clickToElement(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, subMenuPageName);
	}	
	
	public void openMenuPageByName(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		clickToElement(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
	}	
	
	public void clickToButtonByID(WebDriver driver, String buttonID) {
		waitForElementClickable(driver, AdminBasePageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, AdminBasePageUI.BUTTON_BY_ID, buttonID);
		
	}
	
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String dataTableBody, String dataTableHeader, String rowIndex, String headerName) {
		int columnIndex = getSizeElements(driver, AdminBasePageUI.TABLE_BY_DATATABLE_AND_HEADERNAME, dataTableHeader,headerName) + 1;
		waitForElementVisible(driver, AdminBasePageUI.DATATABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, dataTableBody, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, AdminBasePageUI.DATATABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, dataTableBody, rowIndex, String.valueOf(columnIndex));
	}
	
	public void enterToTextboxByIDAtAdminSite(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, AdminBasePageUI.TEXTBOX_BY_ID_AT_ADMIN_SITE, textboxID);
		senkeyToElement(driver, AdminBasePageUI.TEXTBOX_BY_ID_AT_ADMIN_SITE, value, textboxID);	
	}
	
	public void clickToCheckboxAtAdminSite(WebDriver driver, String checkboxID) {
		waitForElementClickable(driver, AdminBasePageUI.CHECKBOX_AT_ADMINSITE, checkboxID);
		checkTheCheckboxOrRadio(driver, AdminBasePageUI.CHECKBOX_AT_ADMINSITE, checkboxID);
	}
	
	public void uncheckedToCheckboxAtAdminSite(WebDriver driver, String checkboxID) {
		waitForElementClickable(driver, AdminBasePageUI.CHECKBOX_AT_ADMINSITE, checkboxID);
		uncheckTheCheckbox(driver, AdminBasePageUI.CHECKBOX_AT_ADMINSITE, checkboxID);
	}
	
	public void selectItemInDropdownByNameAtAdminSite(WebDriver driver, String value, String dropdownID) {
		waitForElementClickable(driver, AdminBasePageUI.DROPDOWN_BY_NAME_AT_ADMINSITE, dropdownID);
		selectDropdownByText(driver, AdminBasePageUI.DROPDOWN_BY_NAME_AT_ADMINSITE, value, dropdownID);
	}
	
	private Alert alert;
	private WebDriverWait explicitWait;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	
	
}

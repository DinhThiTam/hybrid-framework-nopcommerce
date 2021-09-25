package pageObject.nopCommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.DesktopsPageUI;
import pageUIs.nopCommerce.HomePageUI;
import pageUIs.nopCommerce.MyAccountUI;
import pageUIs.nopCommerce.NoteBooksPageUI;
import pageUIs.nopCommerce.SearchPageUI;


public class NotebooksPO extends BasePage{
	private WebDriver driver;

	public NotebooksPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLinkByText(WebDriver driver, String linkText) {
		waitForElementClickable(driver, DesktopsPageUI.PRODUCT_TITLE_BY_TEXT, linkText);
		clickToElement(driver, DesktopsPageUI.PRODUCT_TITLE_BY_TEXT, linkText);
		
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getElements(driver, NoteBooksPageUI.PRODUCT_NAME);
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		List<WebElement> productNameElements = getElements(driver, NoteBooksPageUI.PRODUCT_NAME);
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		System.out.println("Before sort:---------");
		for (String product:productNameText) {
			System.out.println(product);
		}
		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		
		System.out.println("After sort:---------");
		for (String product:productNameText) {
			System.out.println(product);
		}
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isPriceSortAscending() {
		List<WebElement> productPriceElements = getElements(driver, NoteBooksPageUI.PRODUCT_PRICE);
		List<String> productNamePrice = new ArrayList<String>();
		
		for (WebElement productPrice : productPriceElements) {
			String productPriceNumber = productPrice.getText().replace("$", "");
			productNamePrice.add(productPriceNumber);
		}
		
		List<String> productNamePriceClone = new ArrayList<String>(productNamePrice);
		Collections.sort(productNamePriceClone);
		return productNamePrice.equals(productNamePriceClone);
	}

	public boolean isPriceSortDescending() {
		List<WebElement> productPriceElements = getElements(driver, NoteBooksPageUI.PRODUCT_PRICE);
		List<String> productNamePrice = new ArrayList<String>();
		
		for (WebElement productPrice : productPriceElements) {
			String productPriceNumber = productPrice.getText().replace("$", "");
			productNamePrice.add(productPriceNumber);
		}
		
		List<String> productNamePriceClone = new ArrayList<String>(productNamePrice);
		Collections.sort(productNamePriceClone);
		Collections.reverse(productNamePriceClone);
		return productNamePrice.equals(productNamePriceClone);
	}

//	public void openPageByNumber(String pageNumber) {
//		waitForElementClickable(driver,HomePageUI.PAGING_BY_NUMBER, pageNumber);
//		clickToElement(driver,HomePageUI.PAGING_BY_NUMBER, pageNumber);	
//	}
	

}

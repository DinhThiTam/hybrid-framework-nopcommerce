package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	WebDriver driver;

	private PageGenerator() {
	}

	public static LoginPO getLoginPage(WebDriver driver) {

		return new LoginPO(driver);
	}

	public static HomePO getHomePage(WebDriver driver) {

		return new HomePO(driver);
	}

	public static RegisterPO getRegisterPage(WebDriver driver) {

		return new RegisterPO(driver);
	}

	public static MyAccountPO getMyAccountPage(WebDriver driver) {

		return new MyAccountPO(driver);
	}

	public static DesktopsPO getDesktopsPage(WebDriver driver) {

		return new DesktopsPO(driver);
	}

	public static ProductReviewPO getReviewProductPage(WebDriver driver) {

		return new ProductReviewPO(driver);
	}

	public static SearchPO getSearchPage(WebDriver driver) {

		return new SearchPO(driver);

	}

	public static ProductDetailsPO getProductDetailsPage(WebDriver driver) {

		return new ProductDetailsPO(driver);
	}

	public static NotebooksPO getNotebooksPage(WebDriver driver) {

		return new NotebooksPO(driver);
	}

	public static WishlistPO getWishlistPage(WebDriver driver) {

		return new WishlistPO(driver);
	}
	
	public static ComparePO getComparePage(WebDriver driver) {

		return new ComparePO(driver);
	}
	
	public static RecentlyViewrdProductsPO getRecentlyViewedProductsPage(WebDriver driver) {

		return new RecentlyViewrdProductsPO(driver);
	}


}

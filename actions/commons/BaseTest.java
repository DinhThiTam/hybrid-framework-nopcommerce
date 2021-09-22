package commons;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import reportConfig.VerificationFailures;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());	
	}

	String projectLocation = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	private enum BROWSER {
		CHROME, FIREFOX, IE, SAFARI, EDGE_LEGACY, EDGE, H_CHROME, H_FIREFOX; 
	}
	
	private enum OS {
		WINDOWS, MAC_OSX, LINUX;
	}
	
	private enum PLATFORM {
		ANDROID, IOS, WINDOW_PHONE;
	}
	
//	public WebDriver getBrowserDriver(String browserName) {
//		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
//		if (browser==BROWSER.FIREFOX) {
//			WebDriverManager.firefoxdriver().setup();
//			//Check driver version hiện tại là bao nhiêu
//			//Tải về
//			//Init browser 
//			//System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
//			//System.setProperty("webdriver.gecko.driver", projectLocation + getDirectorySlash("browserDrivers")+ "geckodriver.exe");
//			driver = new FirefoxDriver();
//			System.out.println("Driver init at BaseTest" + driver.toString());
//		} else if (browser==BROWSER.CHROME) {
//			WebDriverManager.chromedriver().setup();
//			//System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
//			//System.setProperty("webdriver.gecko.driver", projectLocation + getDirectorySlash("browserDrivers")+ "chromedriver.exe");
//			driver = new ChromeDriver();
//		} else if (browser==BROWSER.EDGE) {
//			WebDriverManager.edgedriver().setup();
//			//System.setProperty("webdriver.edge.driver", projectLocation + "\\browserDrivers\\msedgedriver.exe");
//			//System.setProperty("webdriver.gecko.driver", projectLocation + getDirectorySlash("browserDrivers")+ "msedgedriver.exe");
//			driver = new EdgeDriver();
//		} else {
//			throw new RuntimeException("Please enter browser correct");
//		}
//		
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		
//		return driver;
//	}
	
	public WebDriver getBrowserDriver(String browserName, String appURL) {

		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if (browser==BROWSER.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Driver init at BaseTest" + driver.toString());
		} else if (browser==BROWSER.CHROME) {
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser==BROWSER.EDGE) {
			//System.setProperty("webdriver.edge.driver", projectLocation + "\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Please enter browser correct");
		}
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		return driver;
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}
	private String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else if (isWindows()) {
			folderName = "\\" + folderName + "\\";
		} else {
			folderName = null;
		}
		return folderName;
	}

	private boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	private boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	private boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0);
	}

	private boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	@BeforeTest
	
	public void deleteAllFileInFolder() {

		log.info("--------START delete file in folder---------");
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "/screenshotReportNG";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i=0; i<listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		log.info("--------END delete file in folder---------");
	}
	
	protected void cleanBrowserAndDriver() {
		String cmd = "";
		try {
			
			//Executable driver(gecko, chromedriver.exe..
			// Quit driver executable file in Task Manager
			// Get ra tên của OS và convert qua chữ thường
						String osName = System.getProperty("os.name").toLowerCase();
						log.info("OS name = " + osName);
						// Khai báo 1 biến command line để thực thi
						
						
						if (driver.toString().toLowerCase().contains("chrome")) {
							if (osName.toLowerCase().contains("windows")) {
								cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
							} else {	
								cmd = "pkill chromedriver";
							}
						} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
							if (osName.toLowerCase().contains("window")) {
								cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
							}
						} else if (driver.toString().toLowerCase().contains("firefox")) {
							if (osName.toLowerCase().contains("windows")) {
								cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
							} else {
								cmd = "pkill geckodriver";
								
							}
						} else if (driver.toString().toLowerCase().contains("edge")) {
							if (osName.toLowerCase().contains("windows")) {
								cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
								
							} else {
								cmd = "pkill msedgedriver";
							}
						}
						//Browser
						if(driver!=null) {
							//IE browser
							driver.manage().deleteAllCookies();
							driver.quit();
						}
						
		} catch (Exception e) {
			log.info(e.getMessage());
		}finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}


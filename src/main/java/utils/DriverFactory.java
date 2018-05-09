package utils;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.ContactUs_Page;
import pageObjects.Products_Page;

public class DriverFactory {
	public static WebDriver driver;
	public static ContactUs_Page contactUsPage;
	public static Products_Page productsPage;

	private ReadConfigFile config = new ReadConfigFile();

	public WebDriver getDriver() {
		try {
			String browserName = config.getBrowser();

			switch (browserName) {

			case "firefox":
				// code
				if (null == driver) {
					System.setProperty("webdriver.gecko.driver", Constant.GECKO_DRIVER_DIRECTORY);
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					driver = new FirefoxDriver();
				}
				break;

			case "chrome":
				// code
				if (null == driver) {
					System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_DIRECTORY);
					// CHROME OPTIONS
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}
				break;

			case "ie":
				// code
				if (null == driver) {
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					System.setProperty("webdriver.ie.driver", Constant.IE_DRIVER_DIRECTORY);
					capabilities.setCapability("ignoreZoomSetting", true);
					driver = new InternetExplorerDriver(capabilities);
					driver.manage().window().maximize();
				}
				break;

            case "remote":
                    if (null == driver) {
						// localhost:9515
                        // 188.166.121.188:9515
                        URL url = new URL("http://188.166.121.188:4444/wd/hub");
                        System.out.println("! Using remote Webdirver: " + url);
                        ChromeOptions opts = new ChromeOptions();
                        driver = new RemoteWebDriver(url , opts);
                        driver.manage().window().maximize();
                    }
                break;
			}
		} catch (Exception e) {
			System.out.println("Unable to load browser: " + e.getMessage());
		} finally {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			contactUsPage = PageFactory.initElements(driver, ContactUs_Page.class);
			productsPage = PageFactory.initElements(driver, Products_Page.class);
		}
		return driver;
	}
}

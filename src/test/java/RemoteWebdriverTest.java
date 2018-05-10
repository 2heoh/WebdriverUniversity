import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;

public class RemoteWebdriverTest {
    public static WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        driver = new RemoteWebDriver(
//                new URL("http://localhost:4444/wd/hub"),
                new URL("http://128.199.54.189:4444/wd/hub"),
                new ChromeOptions()
        );
    }

    @Test
    public void WhenIGetPageTitleFromGoogle_ItIsGoogle() {


        driver.get("http://google.com");

        assertEquals("Google", driver.getTitle());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

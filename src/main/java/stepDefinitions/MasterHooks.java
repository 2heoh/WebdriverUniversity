package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;

public class MasterHooks extends DriverFactory {

    @Before
    public void setup() {
        driver = getDriver();
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {

        try {
            if (driver != null) {

                if (scenario.isFailed()) {
                    scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
                }

                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.out.println("Error catched: " + e.getMessage());
        }
    }

}
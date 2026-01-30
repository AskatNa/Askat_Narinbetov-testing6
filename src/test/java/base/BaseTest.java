package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.BrowserStackDriverFactory;


public abstract class BaseTest {
    public WebDriver driver;
    @BeforeMethod
    @Parameters({"browser", "platform", "version"})
    public void setUp(String browser, String platform, String version) throws Exception {
        driver = BrowserStackDriverFactory.createDriver(browser, platform, version);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

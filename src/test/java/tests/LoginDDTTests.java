package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataProviders;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

@Listeners(TestListener.class)
public class LoginDDTTests extends BaseTest {
    @Test(dataProvider = "loginDDT", dataProviderClass = DataProviders.class)
    public void TCLOGIN_DDT(String tcId, String username, String password, String expected) {
        ExtentTestManager.getTest().info("Dataset: " + tcId + " user=" + username + " expected=" + expected);

        LoginPage login = new LoginPage(driver);
        login.open();
        login.login(username, password);

        DashboardPage dashboard = new DashboardPage(driver);

        if ("SUCCESS".equalsIgnoreCase(expected)) {
            Assert.assertTrue(dashboard.isDisplayed(), "Expected SUCCESS, but Dashboard not visible");
            ScreenshotUtil.capture(driver, "DDT_" + tcId + "_SUCCESS");
            ExtentTestManager.getTest().pass("PASSED: " + tcId);
        } else if ("INVALID".equalsIgnoreCase(expected)) {
            String err = login.getErrorTextOrNull();
            Assert.assertTrue(err != null && !err.isBlank(), "Expected INVALID, but no error message shown");
            ScreenshotUtil.capture(driver, "DDT_" + tcId + "_INVALID");
            ExtentTestManager.getTest().pass("PASSED: " + tcId + " error=" + err);
        } else {
            Assert.fail("Unknown expected in Excel: " + expected);
        }
    }
}

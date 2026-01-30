package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

@Listeners(TestListener.class)
public class AdminTests extends BaseTest {

    @Test
    @Parameters({"browser", "platform"})
    public void TCADMIN_001_searchUserAdmin(String browser, String platform) {
        String testName = "Admin Test - " + browser + " on " + platform;
        System.out.println("Running test: " + testName);
        LoginPage login = new LoginPage(driver);
        login.open();
        ExtentTestManager.getTest().info("Step 1: Open login page");
        login.login("Admin", "admin123");
        ExtentTestManager.getTest().info("Step 2: Enter valid username and password");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotUtil.capture(driver, "loggedIn");

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard should be visible");
        ExtentTestManager.getTest().info("Step 3: Verify dashboard page is displayed");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotUtil.capture(driver, "DasboardSeen");
        AdminPage admin = new AdminPage(driver);
        admin.openAdminModule();
        ExtentTestManager.getTest().info("Step 4: Open Admin module");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotUtil.capture(driver, "OpenAdminModule");

        admin.searchUser("Admin");
        ExtentTestManager.getTest().info("Step 5: Search for user 'Admin'");

        Assert.assertTrue(
                admin.isResultDisplayed(),
                "Admin user should appear in search results"
        );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotUtil.capture(driver, "Search for Admin");
        ExtentTestManager.getTest().info("Step 6: Verify search results for 'Admin'");
    }
}

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListener.class)
public class LoginTests extends BaseTest {

    @Test
    public void TCLOGIN_001_validLogin() {
        LoginPage login = new LoginPage(driver);
        login.open();
        ExtentTestManager.getTest().info("Step 1: Open OrangeHRM login page");

        login.login("Admin", "admin123");
        ExtentTestManager.getTest().info("Step 2: Enter valid username and password");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotUtil.capture(driver, "validLogin");
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard should be visible after login");
        ExtentTestManager.getTest().info("Step 3: Verify dashboard page is displayed");
    }

    @Test
    public void TCLOGIN_002_invalidLogin() {
        try {
            LoginPage login = new LoginPage(driver);
            login.open();
            ExtentTestManager.getTest().info("Step 1: Open OrangeHRM login page");

            login.login("Admin", "wrongpass");
            ExtentTestManager.getTest().info("Step 2: Enter valid username and password");

            ScreenshotUtil.capture(driver, "invalidLogin");

            DashboardPage dashboard = new DashboardPage(driver);
            if (!dashboard.isDisplayed()) {
                ExtentTestManager.getTest().fail("Failed to display Dashboard page after login");
            }

            ExtentTestManager.getTest().info("Step 3: Verify dashboard page is displayed");

        } catch (Exception e) {
            ExtentTestManager.getTest().fail("Test failed due to error: " + e.getMessage());
        }}

    @Test
    public void TCLOGOUT_001_validLogout() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("Admin", "admin123");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExtentTestManager.getTest().info("Step 1: Open OrangeHRM login page");

        ScreenshotUtil.capture(driver, "loggedIn");

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard should be visible after login");
        ExtentTestManager.getTest().info("Step 3: Dashboard is displayed");
        dashboard.logout();
        ExtentTestManager.getTest().info("Step 4: Logout performed");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotUtil.capture(driver, "loggedOut");

        Assert.assertTrue(login.isDisplayed(), "Login page should be displayed after logout");
        ExtentTestManager.getTest().pass("Logout test passed");
    }


}

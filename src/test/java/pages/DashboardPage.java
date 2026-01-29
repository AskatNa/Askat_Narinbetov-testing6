import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private static final Logger log = LogManager.getLogger(DashboardPage.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userDropdownTab = By.cssSelector("span.oxd-userdropdown-tab");
    private final By logoutButton = By.linkText("Logout");
    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By dropdownMenu = By.cssSelector("ul.oxd-dropdown-menu");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        log.info("Check if Dashboard page is displayed");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
    }

    public void logout() {
        log.info("Click on Logout button");

        try {
            WebElement userDropdownTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userDropdownTab));
            userDropdownTabElement.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownMenu));
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

            logoutBtn.click();
            log.info("Logout successful");

            ExtentTestManager.getTest().info("Logout button clicked");
        } catch (TimeoutException e) {
            log.error("Logout button or user dropdown was not found in time", e);
            throw e;
        }
    }
}

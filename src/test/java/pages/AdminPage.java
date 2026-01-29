import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    private static final Logger log = LogManager.getLogger(AdminPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By usernameInput = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    private final By searchBtn = By.xpath("//button[.=' Search ']");
    private final By resultRows = By.cssSelector(".oxd-table-body .oxd-table-row");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openAdminModule() {
        log.info("Open Admin module");
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public void searchUser(String username) {
        log.info("Search user: {}", username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));

        driver.findElement(usernameInput).sendKeys(username);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchButton.click();
    }

    public boolean isResultDisplayed() {
        log.info("Check search results");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wait.until(driver -> driver.findElements(resultRows).size() > 0);    }
}

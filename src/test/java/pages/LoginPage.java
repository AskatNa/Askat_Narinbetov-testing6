package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    private WebDriver driver;

    private WebDriverWait wait;
    private By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By errorMsg = By.cssSelector(".oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        log.info("Open OrangeHRM login page");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    public void login(String user, String pass) {
        log.info("Type username");
        driver.findElement(username).sendKeys(user);
        log.info("Type password");
        driver.findElement(password).sendKeys(pass);
        log.info("Click login");
        driver.findElement(loginBtn).click();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public boolean isDisplayed() {
        log.info("Check if Login page is displayed");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(username)).isDisplayed();
    }
    public String getErrorTextOrNull() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
        } catch (Exception e) {
            return null;
        }
    }

}

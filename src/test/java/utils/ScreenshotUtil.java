import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {

    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);

    public static String capture(WebDriver driver, String testName) {
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                String readyState = (String) jsExecutor.executeScript("return document.readyState;");
                if (!"complete".equals(readyState)) {
                    log.warn("Page is not fully loaded, waiting for complete load...");
                    Thread.sleep(2000);
                }
            }
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = Path.of("target", "screenshots", testName + ".png");
            Files.createDirectories(dest.getParent());
            Files.copy(src.toPath(), dest);
            log.info("Screenshot captured: " + dest.toString());
            return dest.toString();
        } catch (Exception e) {
            log.error("Error capturing screenshot for test: " + testName, e);
            return null;
        }
    }
}

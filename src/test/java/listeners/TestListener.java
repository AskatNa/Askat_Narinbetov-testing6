package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("START: {}", result.getName());

        ExtentReports extent = ExtentManager.getExtent();
        ExtentTestManager.setTest(extent.createTest(result.getMethod().getMethodName()));

        ExtentTestManager.getTest().log(Status.INFO, "Test started");

        ExtentTestManager.getTest().log(Status.INFO, "Step 1: Open login page");
        ExtentTestManager.getTest().log(Status.INFO, "Step 2: Enter username and password");
        ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click on login button");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASS: {}", result.getName());
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().pass("Test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAIL: {}", result.getName(), result.getThrowable());

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().fail(result.getThrowable());
        }

        Object instance = result.getInstance();
        if (instance instanceof BaseTest base) {
            String path = ScreenshotUtil.capture(base.driver, result.getName());
            if (path != null && ExtentTestManager.getTest() != null) {
                try {
                    ExtentTestManager.getTest().addScreenCaptureFromPath(path);
                } catch (Exception e) {
                    log.error("Failed to attach screenshot to report", e);
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("SKIP: {}", result.getName());
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().skip("Test skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("=== TEST SUITE END: {} ===", context.getName());
        try {
            ExtentManager.getExtent().flush();
        } catch (Exception e) {
            log.error("Extent flush failed", e);
        }
    }
}

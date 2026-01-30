package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            try {
                Path reportPath = Path.of("target", "extent-report.html");
                Files.createDirectories(reportPath.getParent());

                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath.toString());
                extent = new ExtentReports();
                extent.attachReporter(spark);
            } catch (Exception e) {
                extent = new ExtentReports();
            }
        }
        return extent;
    }
}

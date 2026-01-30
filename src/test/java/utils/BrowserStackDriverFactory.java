package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

import java.net.URL;

public class BrowserStackDriverFactory {
    public static RemoteWebDriver driver = null;
    private static final String USERNAME = "oauth-tophype.0001-21bb2";
    private static final String ACCESS_KEY = "8ce2886d-54c7-4967-b5a6-0ecb9a20aecd";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    @Parameters({"browser", "platform","version"})
    public static RemoteWebDriver createDriver(String browser, String platform, String version) throws MalformedURLException {
        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPlatformName(platform);
            chromeOptions.setBrowserVersion(version);
            URL url = new URL(URL);
            driver = new RemoteWebDriver(url, chromeOptions);
        } else if (browser.equalsIgnoreCase("Edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setPlatformName(platform);
            edgeOptions.setBrowserVersion(version);
            URL url = new URL(URL);
            driver = new RemoteWebDriver(url, edgeOptions);
        }
        return driver;
    }

}

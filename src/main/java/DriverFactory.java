import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static RemoteWebDriver driver;

    public static WebDriver build(String browser) throws MalformedURLException {
        String type = System.getProperty("type");
        if (type.equals("local")) {
            switch (browser) {
                case "chrome":
                    return buildChromeDriver();
                case "firefox":
                    return buildFirefoxDriver();
                default:
                    throw new IllegalStateException("Unexpected value: " + browser);
            }
        } else if (type.equals("remote")) {
            return buildRemoteWebDriver(browser);
        } else {
            throw new IllegalArgumentException(String.format("%s is invalid.", type));
        }
    }

    public static RemoteWebDriver buildRemoteWebDriver(String browser) throws MalformedURLException {

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
                chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
                firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        return driver;
    }

    public static WebDriver buildChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        return new ChromeDriver();
    }

    public static WebDriver buildFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        return new ChromeDriver();
    }
}

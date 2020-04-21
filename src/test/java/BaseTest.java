import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.build(System.getProperty("browser"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

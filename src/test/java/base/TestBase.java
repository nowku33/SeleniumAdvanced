package base;

import models.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import providers.DriverFactory;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.getDriver(Browser.CHROME);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

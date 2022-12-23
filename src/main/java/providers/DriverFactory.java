package providers;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.logging.Logger;

public class DriverFactory {

    private static final Logger log = Logger.getLogger(DriverFactory.class.getName());

    private WebDriver driver = null;

    public WebDriver getDriver (Browser browser) {

        switch(browser) {
            case CHROME:
                this.driver = getChromeDriver();
                break;
            case FIREFOX:
                this.driver = getFirefoxDriver();
                break;
            case EDGE:
                this.driver = getEdgeDriver();
                break;
            case IE:
                this.driver = getIeDriver();
                break;
            default:
                log.info("Browser " + browser + " is not supported");
                System.exit(1);

        }

        this.driver.manage().window().maximize();

        return this.driver;

    }

    private WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    private WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }

    private WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        return driver;
    }

    private WebDriver getIeDriver() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        return driver;
    }
}

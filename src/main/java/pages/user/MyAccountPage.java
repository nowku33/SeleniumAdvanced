package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    WebDriver driver;

    @FindBy(id = "history-link")
    WebElement orderHistory;

    public MyAccountPage(WebDriver driver) {
        this.driver=driver;
        this.driver.get("http://146.59.32.4/index.php?controller=my-account");
        PageFactory.initElements(driver, this);
    }


}

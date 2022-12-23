package pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LogInPage {

    WebDriver driver;

    @FindBy(className = "form-control")
    WebElement emailInput;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(id = "submit-login")
    WebElement signInButton;

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public LogInPage(WebDriver driver) {
        this.driver=driver;
        this.driver.get("http://146.59.32.4/index.php?controller=authentication&back=my-account");
        PageFactory.initElements(driver, this);
    }

    public void logIn (String email, String password) {

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInButton.click();

    }
}

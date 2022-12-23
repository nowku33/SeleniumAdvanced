package pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderNavPage {

    @FindBy(xpath = "//*[contains(text(), 'Sign in')]")
    WebElement signInButton;

    @FindBy(xpath = "//*[contains(text(), 'Cart')]")
    WebElement cartButton;

    WebDriver driver;

    public HeaderNavPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getCartButton() {
        return cartButton;
    }
}

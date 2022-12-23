package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"order-details\"]/ul/li[1]")
    WebElement orderReference;

    public WebElement getOrderReference() {
        return orderReference;
    }

    public String getOrderReferenceNumber() {
        return orderReference.getText().replace("Order reference: ", "");
    }

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}

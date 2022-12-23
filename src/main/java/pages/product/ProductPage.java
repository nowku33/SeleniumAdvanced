package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    @FindBy (id="quantity_wanted")
    WebElement quantityInput;

    @FindBy (xpath = "//button[contains(@class, 'add-to-cart')]")
    WebElement addToCartButton;

    @FindBy (xpath = "//span[contains(@itemprop, 'price')]")
    WebElement productPrice;

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void setQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }


}

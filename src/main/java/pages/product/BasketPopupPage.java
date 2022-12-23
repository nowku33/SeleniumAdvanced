package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPopupPage {

    @FindBy(xpath = "//div[contains(@class, 'cart-content-btn')]/a")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//div[contains(@class, 'cart-content-btn')]/button")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//div/*[contains(@class, 'cart-products-count')]")
    WebElement cartProductsCount;

    @FindBy(xpath = "//span[contains(@class, 'shipping value')]")
    WebElement shippingValue;

    @FindBy(xpath = "//span[contains(@class, 'subtotal value')]")
    WebElement subtotalValue;

    @FindBy(xpath = "//*[contains(@class, 'product-total')]/span[contains(@class, 'value')]")
    WebElement totalValue;

    @FindBy(xpath = "//h6[contains(@class, 'product-name')]")
    WebElement productName;

    WebDriver driver;

    public BasketPopupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public WebElement getCartProductsCount() {
        return cartProductsCount;
    }

    public WebElement getShippingValue() {
        return shippingValue;
    }

    public WebElement getSubtotalValue() {
        return subtotalValue;
    }

    public WebElement getTotalValue() {
        return totalValue;
    }

    public WebElement getProductName() {
        return productName;
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

}

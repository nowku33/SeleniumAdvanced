package pages.product;

import models.Cart;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    @FindBy(xpath = "//li[contains(@class, 'cart-item')]")
    List<WebElement> cartItems;

    @FindBy(id = "cart-subtotal-products")
    WebElement cartSummaryItems;

    @FindBy(id = "cart-subtotal-shipping")
    WebElement cartSummaryShipping;

    @FindBy(className = "btn-primary")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//*[contains(@class, \"cart-total\")]/span[contains(@class, 'value')]")
    WebElement cartSummaryTotalValue;

    WebDriver driver;

    public WebElement getProceedToCheckoutButton() {
        return proceedToCheckoutButton;
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public WebElement getCartSummaryItems() {
        return cartSummaryItems;
    }

    public WebElement getCartSummaryShipping() {
        return cartSummaryShipping;
    }

    public WebElement getCartSummaryTotalValue() {
        return cartSummaryTotalValue;
    }

    public Cart toCart() {

        List <Product> productList = new ArrayList<>();

        for (int i=1; i<=cartItems.size(); i++) {

            String productName = driver.findElement(By.xpath("(//*[contains(@class, \"product-line-info\")]/a)["+i+"]")).getText();
            float productPrice = Float.parseFloat(driver.findElement(By.xpath("(//*[contains(@class, \"current-price\")]/span)["+i+"]")).getText().replace("$", ""));
            int productQuantity = Integer.parseInt(driver.findElement(By.xpath("(//input[contains(@class, 'product-quantity')])["+i+"]")).getAttribute("value"));

            Product product = new Product(productName, productPrice, productQuantity);

            productList.add(product);

        }

        BigDecimal cartTotalValue = new BigDecimal(cartSummaryTotalValue.getText().replaceAll("[^\\d.-]", ""));
        BigDecimal cartShippingValue = new BigDecimal(cartSummaryShipping.getText().replaceAll("[^\\d.-]", ""));

        return new Cart(productList, cartTotalValue, cartShippingValue);

    }

}

package pages.product;

import models.Cart;
import models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage {

    @FindBy(css = "#order-history > table > tbody > tr > td:nth-child(1)")
    WebElement orderDate;

    @FindBy(css = "tr.text-xs-right.line-total > td:nth-child(2)")
    WebElement totalOrderCost;

    @FindBy(css = "tbody > tr > td:nth-child(2) > span")
    WebElement orderStatus;

    @FindBy(css = "#content > div:nth-child(8) > table > tbody > tr > td:nth-child(2)")
    WebElement carrier;

    WebElement deliveryAddress;

    WebElement invoiceAddress;


    WebDriver driver;

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*public Order toOrder() {

        Cart cart = new Cart();



        return new Order.OrderBuilder().setOrderStatus(orderStatus.getText())
                .setCart(cart)
                .setDate(orderDate.getText())
                .setCarrier(carrier.getText())
                .setDeliveryAddress()
                .setInvoiceAddress()
                .build();

    }*/
}

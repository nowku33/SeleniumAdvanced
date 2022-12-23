package pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderHistoryPage {

    WebDriver driver;

    @FindBy(css = "#content > table > tbody > tr")
    List<WebElement> orderList;

    @FindBy(css = "tr> td.text-sm-center.order-actions > a:nth-child(1)")
    List<WebElement> orderDetailsList;

    public OrderHistoryPage(WebDriver driver) {
        driver.get("http://146.59.32.4/index.php?controller=history");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOrderDetails(String referenceNumber) {

        driver.findElement(By.xpath("//*[contains(text(), '"+referenceNumber+"')]")).click();
        int i = 0;
        for (WebElement order : orderList) {
            i++;
            System.out.println("order text: "+order.getText());
            System.out.println("order contains: "+order.getText().contains(referenceNumber));
            if (order.getText().contains(referenceNumber)) {
                driver.findElement(By.cssSelector("tr:nth-child("+i+")> td.text-sm-center.order-actions > a:nth-child(1)")).click();
                break;

            }

        }
    }
}

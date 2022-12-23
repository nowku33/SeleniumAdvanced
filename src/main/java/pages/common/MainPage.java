package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage {

    //@FindBy(xpath = "//*[contains(@class, 'products row')]/div")
    @FindBy(xpath = "//*[contains(@class, 'h3 product-title')]")
    private List<WebElement> popularProductsList;

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver=driver;
        this.driver.get("http://146.59.32.4/index.php");
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getPopularProductsList() {
        return popularProductsList;
    }

    public List<String> getPopularProductsNames() {

        List<String> popularProductsNames = new ArrayList<>();

        List<WebElement> popularProducts = driver.findElements(By.xpath("//a[contains(@itemprop, 'url')]"));

        for (WebElement product : popularProducts) {
            popularProductsNames.add(product.getText());
        }

        return popularProductsNames;

    }

}

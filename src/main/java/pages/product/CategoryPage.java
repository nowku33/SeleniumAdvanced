package pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CategoryPage {

    @FindBy(xpath = "//*[@id=\"js-product-list-header\"]/div/h1")
    WebElement currentCategoryHeader;

    //@FindBy(xpath = "//div[contains(@itemprop, 'itemListElement')]")
    @FindBy(className = "product-title")
    List<WebElement> allProducts;

    @FindBy(xpath = "//li[contains(@class, 'filter-block')]")
    WebElement filterBlock;

    WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(allProducts));
        return allProducts;
    }

    public String getCurrentCategoryHeaderText() {

        return currentCategoryHeader.getText();
    }

    public WebElement getCurrentCategoryHeader() {

        return currentCategoryHeader;
    }

    public int getTotalProductNumber() {

        return allProducts.size();
    }

    public String getNrOfProductsText() {
        return driver.findElement(By.xpath("//*[@id=\"js-product-list-top\"]/div[1]/p")).getText();
    }

    public void setSliderPriceRange(float minPrice, float maxPrice) {

        WebElement slider = driver.findElement(By.xpath("//ul[contains(@class, 'slider')]"));
        int sliderWidth=slider.getSize().getWidth();

        int sliderMinPrice = Integer.parseInt(slider.getAttribute("data-slider-min"));
        int sliderMaxPrice = Integer.parseInt(slider.getAttribute("data-slider-max"));
        int priceDifference = sliderMaxPrice-sliderMinPrice;

        int moveLeftSliderBy = (int) (minPrice - sliderMinPrice)*(sliderWidth)/(priceDifference);
        int moveRightSliderBy = (int) (sliderMaxPrice - maxPrice)*(sliderWidth)/(priceDifference);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (maxPrice > sliderMaxPrice) {
            maxPrice = sliderMaxPrice;
        }
        if (minPrice < sliderMinPrice) {
            minPrice = sliderMinPrice;
        }

        WebElement leftSlider = driver.findElement(By.xpath("(//*[contains(@id, 'slider-range')]/a)[1]"));

        new Actions(driver).dragAndDropBy(leftSlider, moveLeftSliderBy,0).perform();

        wait.until(ExpectedConditions.textToBePresentInElement(filterBlock,String.valueOf(minPrice)));

        WebElement rightSlider = driver.findElement(By.xpath("(//*[contains(@id, 'slider-range')]/a)[2]"));

        new Actions(driver).dragAndDropBy(rightSlider, -moveRightSliderBy,0).perform();

        wait.until(ExpectedConditions.textToBePresentInElement(filterBlock,String.valueOf(maxPrice)));

    }

    public void clickOnProduct(String productName) {

        for (WebElement product : allProducts) {
            if (product.getText().equals(productName)) {
                product.click();
                break;
            }
        }
        System.out.println("The product has not been found");
    }

}

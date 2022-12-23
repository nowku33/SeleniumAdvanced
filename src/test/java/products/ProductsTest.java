package products;

import base.TestBase;
import helpers.YmlHandler;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.HeaderTopPage;
import pages.product.CategoryPage;
import providers.YmlFiles;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

class ProductsTest extends TestBase {

    //@RepeatedTest(10)
    void categoriesTest() throws IOException {

        YmlHandler ymlFile = new YmlHandler(YmlFiles.PRODUCTS_TEST_DATA);
        String url = ymlFile.getProperty("url");

        driver.get(url);
        HeaderTopPage headerTopPage = new HeaderTopPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        SoftAssertions softAssertions = new SoftAssertions();

        //get main category names
        List<String> mainCategoryNames = headerTopPage.getMainCategoryNames();

        //iterate categories list
        for (String mainCategoryName : mainCategoryNames) {

            //click on selected category
            headerTopPage.clickOnCategory(mainCategoryName);
            String currentCategoryHeader = categoryPage.getCurrentCategoryHeaderText();

            //checking if current category header is equal to the one clicked
            softAssertions.assertThat(currentCategoryHeader).isEqualTo(mainCategoryName);

            //number of all products in category
            int totalProductsNumber = categoryPage.getTotalProductNumber();

            String actualNrOfProductsText = categoryPage.getNrOfProductsText();
            String expectedNrOfProductsText = "There are "+ totalProductsNumber +" products.";

            softAssertions.assertThat(actualNrOfProductsText).isEqualTo(expectedNrOfProductsText);
        }

        softAssertions.assertAll();
    }

    @RepeatedTest(5)
    void filterTests() throws InterruptedException, IOException {

        YmlHandler ymlFile = new YmlHandler(YmlFiles.PRODUCTS_TEST_DATA);

        float minPrice = Float.parseFloat(ymlFile.getProperty("minPrice"));
        float maxPrice = Float.parseFloat(ymlFile.getProperty("maxPrice"));
        String category = ymlFile.getProperty("category");
        String url = ymlFile.getProperty("url");

        driver.get(url);
        HeaderTopPage headerTopPage = new HeaderTopPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        SoftAssertions softAssertions = new SoftAssertions();

        headerTopPage.clickOnCategory(category);

        categoryPage.setSliderPriceRange(minPrice, maxPrice);
        //TimeUnit.SECONDS.sleep(2);

        List<WebElement> allProducts = categoryPage.getAllProducts();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(@class, 'price')]")));

        if (allProducts.size() > 0) {
            for (int i = 1; i <= allProducts.size(); i++) {

                System.out.println("i: " + i);
                By productPriceXpath = By.xpath("(//span[contains(@class, 'price')])[" + i + "]");

                String productPrice = driver.findElement(productPriceXpath).getText();
                productPrice = productPrice.replace("$", "");

                System.out.println("Price: " + productPrice);

                softAssertions.assertThat(Float.valueOf(productPrice)).isGreaterThanOrEqualTo(minPrice);
                softAssertions.assertThat(Float.valueOf(productPrice)).isLessThanOrEqualTo(maxPrice);

            }
        }
        else { System.out.println("There are no products in price range: "+minPrice+" - "+maxPrice);}

        softAssertions.assertAll();

    }
}

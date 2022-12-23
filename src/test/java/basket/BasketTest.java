package basket;

import base.TestBase;
import helpers.YmlHandler;
import models.Cart;
import models.Product;
import org.apache.commons.logging.Log;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.HeaderNavPage;
import pages.common.HeaderTopPage;
import pages.common.MainPage;
import pages.product.BasketPopupPage;
import pages.product.CartPage;
import pages.product.CategoryPage;
import pages.product.ProductPage;
import providers.YmlFiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.logging.*;

import static org.assertj.core.api.Assertions.assertThat;

class BasketTest extends TestBase {

    private static Logger Log = Logger.getLogger(Log.class.getName());

    //@RepeatedTest(10)
    void basketPopupTest() throws IOException {

        YmlHandler ymlFile = new YmlHandler(YmlFiles.BASKET_POPUP_TEST_DATA);

        String category = ymlFile.getProperty("category");
        String url = ymlFile.getProperty("url");
        String product = ymlFile.getProperty("product");
        String expectedProductsCountText = ymlFile.getProperty("expectedProductsCountText");
        int quantity = Integer.valueOf(ymlFile.getProperty("quantity"));
        float shippingPrice = Float.parseFloat(ymlFile.getProperty("shippingPrice"));
        String currency = ymlFile.getProperty("currency");

        SoftAssertions softAssertions = new SoftAssertions();
        DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols();
        decimalSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("0.00", decimalSymbols);
        decimalFormat.setMaximumFractionDigits(2);

        driver.get(url);

        HeaderTopPage headerTopPage = new HeaderTopPage(driver);

        headerTopPage.clickOnCategory(category);

        CategoryPage categoryPage = new CategoryPage(driver);

        List<WebElement> allProducts = categoryPage.getAllProducts();

        int i = 0;
        for (WebElement productFromList : allProducts) {

            i++;
            String productTitle = productFromList.findElement(By.xpath("(//*[contains(@class, 'product-title')])[" + i + "]")).getText();

            if (productTitle.equals(product)) {
                productFromList.click();
                break;
            }
        }

        ProductPage productPage = new ProductPage(driver);

        productPage.setQuantity(quantity);
        productPage.clickAddToCart();

        BasketPopupPage basketPopupPage = new BasketPopupPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(basketPopupPage.getSubtotalValue()));

        String actualPriceWithoutShipping = basketPopupPage.getSubtotalValue().getText();
        float expectedPriceWithoutShipping = Float.parseFloat(productPage.getProductPrice().replace(currency, ""));

        Log.info("actual: " + actualPriceWithoutShipping);
        Log.info("expected: " + currency + decimalFormat.format(expectedPriceWithoutShipping * quantity));
        Log.info("carts count txt: " + basketPopupPage.getCartProductsCount().getText());


        softAssertions.assertThat(basketPopupPage.getCartProductsCount().getText()).isEqualTo(expectedProductsCountText, quantity);
        softAssertions.assertThat(basketPopupPage.getProductName().getText()).isEqualTo(product);
        softAssertions.assertThat(basketPopupPage.getShippingValue().getText()).isEqualTo(currency + decimalFormat.format(shippingPrice));

        softAssertions.assertAll();

    }

    @RepeatedTest(10)
    void BasketCalculationTest() throws IOException {

        YmlHandler ymlFile = new YmlHandler(YmlFiles.BASKET_CALCULATIONS_TEST_DATA);

        String url = ymlFile.getProperty("url");
        String currency = ymlFile.getProperty("currency");
        int productQuantityMin = Integer.parseInt(ymlFile.getProperty("productQuantityMin"));
        int productQuantityMax = Integer.parseInt(ymlFile.getProperty("productQuantityMax"));
        int numberOfProducts = Integer.parseInt(ymlFile.getProperty("numberOfProducts"));
        BigDecimal shippingPrice = new BigDecimal(ymlFile.getProperty("shippingPrice"));

        MainPage mainPage = new MainPage(driver);
        Random rand = new Random();
        ProductPage productPage = new ProductPage(driver);
        BasketPopupPage basketPopupPage = new BasketPopupPage(driver);

        //ger popular product names from main page
        List<WebElement> popularProducts = mainPage.getPopularProductsList();

        Cart expectedCart = new Cart();
        expectedCart.setShippingPrice(shippingPrice);

        for (int i = 1; i <= numberOfProducts; i++) {

            driver.get(url);
            //System.out.println("Choosing "+i+" product");
            Log.info("Choosing " + i + " product");

            //choose random product name
            WebElement randomProduct = popularProducts.get(rand.nextInt(popularProducts.size()));
            String randomProductName = randomProduct.getText();
            //System.out.println("Random product name: " + randomProductName);
            Log.info("Random product name: " + randomProductName);
            randomProduct.click();

            //choose random product quantity
            rand.nextInt(productQuantityMax);
            int randomQuantity = rand.nextInt(productQuantityMax - productQuantityMin) + productQuantityMin;
            Log.info("random quantity: " + randomQuantity);
            productPage.setQuantity(randomQuantity);

            Float randomProductPrice = Float.parseFloat(productPage.getProductPrice().replace(currency, ""));

            Product product = new Product(randomProductName, randomProductPrice, randomQuantity);

            //add product to cart
            productPage.clickAddToCart();
            expectedCart.addProduct(product);

            //continue shopping
            basketPopupPage.clickContinueShoppingButton();

        }

        //header nav page with log in and cart buttons
        HeaderNavPage headerNavPage = new HeaderNavPage(driver);

        //click on cart button
        headerNavPage.getCartButton().click();

        CartPage cartPage = new CartPage(driver);

        for (Product cartItem : expectedCart.getProductList()) {

            Log.info("iterate list, expected cart item name: " + cartItem.getProductName());

        }

        Cart actualCart = cartPage.toCart();


        Log.info("actual cart product name: " + expectedCart);

        for (int i = 0; i < expectedCart.getProductList().size(); i++) {

            Log.info("actual cart product name: " + actualCart.getProductList().get(i).getProductName());
            Log.info("expected cart product name: " + expectedCart.getProductList().get(i).getProductName());
            Log.info("actual cart product price: " + actualCart.getProductList().get(i).getProductPrice());
            Log.info("expected cart product price: " + expectedCart.getProductList().get(i).getProductPrice());
            Log.info("actual cart product quantity: " + actualCart.getProductList().get(i).getProductQuantity());
            Log.info("expected cart product quantity: " + expectedCart.getProductList().get(i).getProductQuantity());

            i++;

        }

        assertThat(actualCart).usingRecursiveComparison().isEqualTo(expectedCart);

    }


}

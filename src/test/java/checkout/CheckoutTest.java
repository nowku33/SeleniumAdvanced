package checkout;

import base.TestBase;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.RepeatedTest;
import helpers.YmlHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.common.HeaderTopPage;
import pages.product.*;
import pages.user.LogInPage;
import providers.YmlFiles;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CheckoutTest extends TestBase {

    private static final Logger log = Logger.getLogger(ClassOrderer.ClassName.class.getName());

    @RepeatedTest(1)
    public void OrderHistoryTest() throws FileNotFoundException, InterruptedException {

        YmlHandler ymlFile = new YmlHandler(YmlFiles.CHECKOUT_TEST_DATA);

        Map<String, String> testData = ymlFile.readDataFromYml();

        LogInPage logInPage = new LogInPage(driver);

        logInPage.logIn( testData.get("email"), testData.get("password"));

        HeaderTopPage headerTopPage = new HeaderTopPage(driver);
        headerTopPage.clickOnCategory(testData.get("category"));

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.clickOnProduct(testData.get("product"));

        ProductPage productPage = new ProductPage(driver);
        productPage.clickAddToCart();

        BasketPopupPage basketPopupPage = new BasketPopupPage(driver);
        basketPopupPage.clickProceedToCheckoutButton();

        CartPage cartPage = new CartPage(driver);

        cartPage.getProceedToCheckoutButton().click();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.getBillingDiffersFromShipping().click();

        try {
            orderPage.getInvoiceAddresses().isDisplayed();
            System.out.println("Shipping address is already provided");
        } catch (Exception e) {
            System.out.println("invoice addresses are not displayed. Filling form");
            orderPage.typeAddress(testData.get("address"));
            orderPage.typePostCode(testData.get("postCode"));
            orderPage.typeCity(testData.get("city"));
        }

        orderPage.clickConfirmAddressBtn();
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert");
        }

        orderPage.clickConfirmShippingMethodBtn();
        orderPage.clickPayByCheck();
        orderPage.clickTermsOfServiceCheckbox();
        orderPage.clickPlaceOrderBtn();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        String orderReferenceNumber = orderConfirmationPage.getOrderReferenceNumber();

        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.clickOrderDetails(orderReferenceNumber);


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

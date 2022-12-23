package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

    WebDriver driver;

    @FindBy(name= "confirm-addresses")
    WebElement confirmAddressBtn;

    @FindBy(css = "a[data-link-action='different-invoice-address']")
    WebElement billingDiffersFromShipping;

    @FindBy(css = "alert alert-danger")
    WebElement noCarriersAvailableAlert;

    @FindBy(id = "invoice-addresses")
    WebElement invoiceAddresses;

    @FindBy(name = "address1")
    WebElement addressInput;

    @FindBy(name = "postcode")
    WebElement postCodeInput;

    @FindBy(name = "city")
    WebElement cityInput;

    @FindBy(name = "confirmDeliveryOption")
    WebElement confirmShippingMethodBtn;

    @FindBy(id = "payment-option-1")
    WebElement payByCheckRadioBtn;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement termsOfServiceCheckbox;

    @FindBy(xpath = "//*[@id=\"payment-confirmation\"]/div[1]/button")
    WebElement paymentConfirmationBtn;

    public WebElement getInvoiceAddresses() {
            return invoiceAddresses;
    }

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public WebElement getBillingDiffersFromShipping() {
        return billingDiffersFromShipping;
    }

    public void typeAddress(String address) {

        addressInput.sendKeys(address);
    }

    public void typePostCode(String postCode) {

        postCodeInput.sendKeys(postCode);
    }

    public void typeCity(String city) {

        cityInput.sendKeys(city);
    }

    public void clickConfirmAddressBtn() {
        confirmAddressBtn.click();
    }

    public void clickConfirmShippingMethodBtn() {
        confirmShippingMethodBtn.click();
    }

    public void clickPayByCheck() {
        payByCheckRadioBtn.click();
    }

    public void clickTermsOfServiceCheckbox() {
        termsOfServiceCheckbox.click();

    }

    public void clickPlaceOrderBtn() {
        paymentConfirmationBtn.click();
    }

}

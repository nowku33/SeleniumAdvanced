package pages.common;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HeaderTopPage {

    private static final Logger log = Logger.getLogger(HeaderTopPage.class.getName());

    @FindBy(name = "s")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[contains(@class, 'products row')]/div")
    private List<WebElement> popularProducts;

    @FindBy(xpath = "//a[contains(@itemprop, 'url')]")
    private List<String> popularProductsNames;

    @FindBy(xpath = "//*[contains(@class, 'dropdown-item') and not(contains(@class, 'submenu'))]")
    private List<WebElement> mainCategories;

    WebDriver driver;

    public HeaderTopPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getMainCategoryNames() {

        List<String> mainCategoryNames = new ArrayList<>();

        for (WebElement category : mainCategories) {

            mainCategoryNames.add(category.getText());

        }

        return mainCategoryNames;
    }

    public List<WebElement> getSearchDropdownList() {
        return driver.findElements(By.xpath("//*[starts-with(@id,\"ui-id-\")]/span[3]"));
    }

    public List<String> getSearchDropdownProductNames() {
        List<WebElement> searchDropdownList = getSearchDropdownList();
        List <String> searchDropdownProductNames = new ArrayList<>();
        for (WebElement searchListElement : searchDropdownList) {

            searchDropdownProductNames.add(searchListElement.getText());

        }

        return searchDropdownProductNames;
    }

    public void searchInput(String searchText) {
        searchInput.sendKeys(searchText);

    }

    public void clickSearchButton() {
        searchButton.click();

    }

    public void clickOnCategory(String categoryName) {

        String capitalizedCategoryName = StringUtils.capitalize(categoryName.toLowerCase());
        WebElement category = driver.findElement(By.xpath("//*[contains(.,'"+capitalizedCategoryName+"')]/a"));

        log.info("Category to click: " + category.getText());

        category.click();

    }
}

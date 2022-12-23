package search;

import base.TestBase;
import helpers.YmlHandler;
import org.junit.jupiter.api.ClassOrderer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.HeaderTopPage;
import providers.YmlFiles;
import org.assertj.core.api.SoftAssertions;

import org.junit.jupiter.api.RepeatedTest;
import pages.common.MainPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

class SearchTest extends TestBase {

    private static final Logger log = Logger.getLogger(ClassOrderer.ClassName.class.getName());

    // This test fails for randomProductName: THE BEST IS YET POSTER
    //@RepeatedTest(10)
    void standardSearch() {

        MainPage mainPage = new MainPage(driver);
        HeaderTopPage headerTopPage = new HeaderTopPage(driver);
        Random rand = new Random();
        SoftAssertions softAssertions = new SoftAssertions();

        //ger popular product names from main page
        List<String> popularProductNames = mainPage.getPopularProductsNames();

        //choose random product name
        String randomProductName = popularProductNames.get(rand.nextInt(popularProductNames.size()));

        log.info("Random product name: " + randomProductName);

        //type random product name to search input
        headerTopPage.searchInput(randomProductName);

        //wait for dropdown
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(headerTopPage.getSearchDropdownList().get(0)));

        String firstProductFromSearch = headerTopPage.getSearchDropdownList().get(0).getText();
        int searchDropdownSize = headerTopPage.getSearchDropdownList().size();

        //Asserions
        softAssertions.assertThat(firstProductFromSearch).isEqualTo(randomProductName);
        softAssertions.assertThat(searchDropdownSize).isEqualTo(1);

        log.info("First product from search: "+firstProductFromSearch);

        //click search button
        headerTopPage.clickSearchButton();

        softAssertions.assertAll();

    }

    @RepeatedTest(10)
    void dropdownSearch() throws IOException {

        MainPage mainPage = new MainPage(driver);
        HeaderTopPage headerTopPage = new HeaderTopPage(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        YmlHandler ymlFile = new YmlHandler(YmlFiles.SEARCH_FILE);
        String searchText = ymlFile.getProperty("toSearch");

        log.info("Searching for: "+searchText);

        headerTopPage.searchInput(searchText);

        //wait for dropdown list
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(headerTopPage.getSearchDropdownList()));

        List<String> searchDropdownResults = headerTopPage.getSearchDropdownProductNames();

        for (String searchResult : searchDropdownResults) {

            softAssertions.assertThat(searchResult).contains(searchText);

        }

        softAssertions.assertAll();

    }

}

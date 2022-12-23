package providers;

//files to read
public enum YmlFiles {

    USERS_FILE("Users.yml"),
    SEARCH_FILE("Search.yml"),

    PRODUCTS_TEST_DATA("ProductsTestData.yml"),

    BASKET_POPUP_TEST_DATA("BasketPopupTestData.yml"),

    BASKET_CALCULATIONS_TEST_DATA("BasketCalculationsTestData.yml"),

    CHECKOUT_TEST_DATA("CheckoutTestData.yml");

    public final String fileName;

    private YmlFiles(String fileName) {
        this.fileName = fileName;
    }

}

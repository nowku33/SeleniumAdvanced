package models;

public class Product {

    String productName;
    float productPrice;
    int productQuantity;

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public Product(String productName, float productPrice, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
}

package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Cart {

    private static Logger log = Logger.getLogger(Cart.class.getName());

    private static final int SCALE = 2;

    private static final RoundingMode roundingMode = RoundingMode.HALF_DOWN;

    private BigDecimal shippingPrice = BigDecimal.ZERO.setScale(SCALE, roundingMode);

    List<Product> productList = new ArrayList<>();

    BigDecimal totalOrderCost = BigDecimal.ZERO.setScale(SCALE, roundingMode);

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.totalOrderCost = totalOrderCost.add(shippingPrice).setScale(SCALE,roundingMode);
        this.shippingPrice = shippingPrice.setScale(SCALE, roundingMode);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost.setScale(SCALE,roundingMode);
    }

    public Cart() {

    }

    public Cart(List<Product> productList, BigDecimal totalOrderCost, BigDecimal shippingPrice) {
        this.productList = productList;
        this.totalOrderCost = totalOrderCost.setScale(SCALE, roundingMode);
        this.shippingPrice = shippingPrice;
    }

    public void addProduct(Product product){

        boolean productExist = false;

        log.info("checking if product is on product list");
        for (Product productFromList : productList) {

            if (productFromList.getProductName().equals(product.getProductName())) {
                log.info("Cart total order cost BEFORE increasing quantity of the product: " + totalOrderCost);
                log.info("Product price: "+product.getProductPrice());
                log.info("product exists, increasing quantity form "+productFromList.getProductQuantity());
                productFromList.setProductQuantity(productFromList.getProductQuantity()+product.getProductQuantity());
                log.info(" to "+productFromList.getProductQuantity());
                totalOrderCost = totalOrderCost.add(BigDecimal.valueOf(product.getProductPrice()*product.getProductQuantity())).setScale(SCALE, roundingMode);
                log.info("Cart total order cost AFTER increasing quantity of the product: " + totalOrderCost);
                productExist=true;
                break;
            }

        }
        if (!productExist) {
            log.info("adding "+ product.getProductName() +" to cart");
            log.info("Product price: "+product.getProductPrice());
            productList.add(product);
            totalOrderCost = totalOrderCost.add(BigDecimal.valueOf(product.getProductPrice()*product.getProductQuantity())).setScale(SCALE, roundingMode);
        }

        log.info("Cart total order cost after adding the product: " + totalOrderCost);

    }

}

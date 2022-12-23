package models;

public class Order {

    private Cart cart;
    private String date;
    private Address deliveryAddress;
    private Address invoiceAddress;
    private String orderStatus;
    private String carrier;

    private Order(OrderBuilder builder) {
        this.cart = builder.cart;
        this.date = builder.date;
        this.deliveryAddress = builder.deliveryAddress;
        this.invoiceAddress = builder.invoiceAddress;
        this.orderStatus = builder.orderStatus;
        this.carrier = builder.carrier;
    }

    public static class OrderBuilder {
        private Cart cart;
        private String date;
        private Address deliveryAddress;
        private Address invoiceAddress;
        private String orderStatus;
        private String carrier;

        public OrderBuilder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public OrderBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public OrderBuilder setDeliveryAddress(Address deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public OrderBuilder setInvoiceAddress(Address invoiceAddress) {
            this.invoiceAddress = invoiceAddress;
            return this;
        }

        public OrderBuilder setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder setCarrier(String carrier) {
            this.carrier = carrier;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
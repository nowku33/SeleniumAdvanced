package models;

public class Address {

    private final String userName;
    private final String address;
    private final String city;
    private final String postCode;
    private final String country;

    private Address(AddressBuilder builder) {
        this.userName = builder.userName;
        this.address = builder.address;
        this.city = builder.city;
        this.postCode = builder.postCode;
        this.country = builder.country;
    }

    public static class AddressBuilder {
        private final String address;
        private final String city;
        private final String postCode;
        private String userName;

        private String country;

        public AddressBuilder(String address, String city, String postCode) {
            this.address = address;
            this.city = city;
            this.postCode = postCode;
        }

        public AddressBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public AddressBuilder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

}

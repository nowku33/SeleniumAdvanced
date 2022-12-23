package models;

import providers.UserBuilder;

public class User {

    private String firstName;
    private String lastName;
    private String email;

    private String country;
    private String city;
    private String zipCode;

    public User(UserBuilder builder) {
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.email = builder.getEmail();
        this.country = builder.getCountry();
        this.city = builder.getCity();
        this.zipCode = builder.getZipCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}

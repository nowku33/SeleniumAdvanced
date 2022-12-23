package providers;

import models.User;

public final class UserBuilder {

    private String firstName;
    private String lastName;
    private String email;

    private String country;
    private String city;
    private String zipCode;

    public UserBuilder(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public User build() {
        return new User(this);
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

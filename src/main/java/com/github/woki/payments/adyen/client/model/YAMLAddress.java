package com.github.woki.payments.adyen.client.model;

import com.github.woki.payments.adyen.model.Address;
import com.github.woki.payments.adyen.model.AddressBuilder;
import com.neovisionaries.i18n.CountryCode;

/**
 * Created by Willian Oki on 9/28/15.
 */
public class YAMLAddress {
    private String city;
    private String countryCode;
    private String number;
    private String postalCode;
    private String state;
    private String street;

    public Address toAddress() {
        return AddressBuilder
                .street(street)
                .numberOrName(number)
                .postalCode(postalCode)
                .city(city)
                .state(state)
                .country(CountryCode.valueOf(countryCode))
                .build();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

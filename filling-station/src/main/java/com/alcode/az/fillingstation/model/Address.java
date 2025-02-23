package com.alcode.az.fillingstation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;

public class Address {

    private final LongProperty addressId = new SimpleLongProperty();
    private final StringProperty street = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final StringProperty state = new SimpleStringProperty();

    public Address() {
    }
    public Address(long addressId, String street, String city, String state) {
        this.addressId.set(addressId);
        this.street.set(street);
        this.city.set(city);
        this.state.set(state);
    }

    // Getters and Setters for properties
    @JsonIgnore
    public long getAddressId() { return addressId.get(); }
    @JsonProperty
    public void setAddressId(long addressId) { this.addressId.set(addressId); }
    public LongProperty addressIdProperty() { return addressId; }

    public String getStreet() { return street.get(); }
    public void setStreet(String street) { this.street.set(street); }
    public StringProperty streetProperty() { return street; }

    public String getCity() { return city.get(); }
    public void setCity(String city) { this.city.set(city); }
    public StringProperty cityProperty() { return city; }

    public String getState() { return state.get(); }
    public void setState(String state) { this.state.set(state); }
    public StringProperty stateProperty() { return state; }

    // Repeat for other properties...
}
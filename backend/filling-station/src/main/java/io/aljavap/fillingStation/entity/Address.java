package io.aljavap.fillingStation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId; // Changed to Long for better compatibility with JPA

    @Version
    private Integer version;
    
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    // Default constructor (required by JPA)
    public Address() {
    }


    public Address(Long addressId, Integer version, String street, String city, String state) {
		super();
		this.addressId = addressId;
		this.version = version;
		this.street = street;
		this.city = city;
		this.state = state;
	}


	// Getters and Setters
    public Long getAddressId() {
        return addressId;
    }

    
    
    public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", version=" + version + ", street=" + street + ", city=" + city
				+ ", state=" + state + "]";
	}

    
}
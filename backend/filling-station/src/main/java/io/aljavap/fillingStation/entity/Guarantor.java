package io.aljavap.fillingStation.entity;


import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "guarantors")
public class Guarantor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guarantorId; // Changed to Long for better compatibility with JPA

    @Version
    private Integer version;
    
    @Column(nullable = false)
    private String guarantorSurname;

    @Column(nullable = false)
    private String guarantorFirstName;

    private String guarantorOtherName;

    @Column(nullable = false)
    private String guarantorPhoneNumber;

    @Column(nullable = false)
    private String guarantorOccupation;

    @Column(nullable = false)
    private String guarantorGender;

    @Column(nullable = false)
    private String guarantorRelationship;

    @Column(nullable = false)
    private LocalDate guarantorDateOfBirth; // Changed to LocalDate for better date handling

    @Column(name = "guarantor_NIN")
    private String guarantorNIN; 
    
    @OneToOne(cascade = CascadeType.ALL) // One-to-one relationship with Address
    @JoinColumn(name = "guarantor_address", referencedColumnName = "addressId")
    private Address guarantorAddress;


    // Default constructor (required by JPA)
    public Guarantor() {
    }



    public Guarantor(Long guarantorId, Integer version, String guarantorSurname, String guarantorFirstName,
			String guarantorOtherName, String guarantorPhoneNumber, String guarantorOccupation, String guarantorGender,
			String guarantorRelationship, LocalDate guarantorDateOfBirth, String guarantorNIN,
			Address guarantorAddress) {
		super();
		this.guarantorId = guarantorId;
		this.version = version;
		this.guarantorSurname = guarantorSurname;
		this.guarantorFirstName = guarantorFirstName;
		this.guarantorOtherName = guarantorOtherName;
		this.guarantorPhoneNumber = guarantorPhoneNumber;
		this.guarantorOccupation = guarantorOccupation;
		this.guarantorGender = guarantorGender;
		this.guarantorRelationship = guarantorRelationship;
		this.guarantorDateOfBirth = guarantorDateOfBirth;
		this.guarantorNIN = guarantorNIN;
		this.guarantorAddress = guarantorAddress;
	}



	// Getters and Setters
    public Long getGuarantorId() {
        return guarantorId;
    }

    
    
    public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getGuarantorSurname() {
        return guarantorSurname;
    }

    public void setGuarantorSurname(String guarantorSurname) {
        this.guarantorSurname = guarantorSurname;
    }

    public String getGuarantorFirstName() {
        return guarantorFirstName;
    }

    public void setGuarantorFirstName(String guarantorFirstName) {
        this.guarantorFirstName = guarantorFirstName;
    }

    public String getGuarantorOtherName() {
        return guarantorOtherName;
    }

    public void setGuarantorOtherName(String guarantorOtherName) {
        this.guarantorOtherName = guarantorOtherName;
    }

    public String getGuarantorPhoneNumber() {
        return guarantorPhoneNumber;
    }

    public void setGuarantorPhoneNumber(String guarantorPhoneNumber) {
        this.guarantorPhoneNumber = guarantorPhoneNumber;
    }

    public String getGuarantorOccupation() {
        return guarantorOccupation;
    }

    public void setGuarantorOccupation(String guarantorOccupation) {
        this.guarantorOccupation = guarantorOccupation;
    }

    public String getGuarantorGender() {
        return guarantorGender;
    }

    public void setGuarantorGender(String guarantorGender) {
        this.guarantorGender = guarantorGender;
    }

    public String getGuarantorRelationship() {
        return guarantorRelationship;
    }

    public void setGuarantorRelationship(String guarantorRelationship) {
        this.guarantorRelationship = guarantorRelationship;
    }

    public LocalDate getGuarantorDateOfBirth() {
        return guarantorDateOfBirth;
    }

    public void setGuarantorDateOfBirth(LocalDate guarantorDateOfBirth) {
        this.guarantorDateOfBirth = guarantorDateOfBirth;
    }

    public Address getGuarantorAddress() {
        return guarantorAddress;
    }

    public void setGuarantorAddress(Address guarantorAddress) {
        this.guarantorAddress = guarantorAddress;
    }

    @Override
    public String toString() {
        return "Guarantor{" +
                "guarantorId=" + guarantorId +
                ", guarantorSurname='" + guarantorSurname + '\'' +
                ", guarantorFirstName='" + guarantorFirstName + '\'' +
                ", guarantorOtherName='" + guarantorOtherName + '\'' +
                ", guarantorPhoneNumber='" + guarantorPhoneNumber + '\'' +
                ", guarantorOccupation='" + guarantorOccupation + '\'' +
                ", guarantorGender='" + guarantorGender + '\'' +
                ", guarantorRelationship='" + guarantorRelationship + '\'' +
                ", guarantorDateOfBirth=" + guarantorDateOfBirth +
                ", guarantorNIN='" + guarantorNIN + '\'' +
                ", guarantorAddress='" + guarantorAddress + '\'' +
                '}';
    }

	public String getGuarantorNIN() {
		return guarantorNIN;
	}

	public void setGuarantorNIN(String guarantorNIN) {
		this.guarantorNIN = guarantorNIN;
	}
}
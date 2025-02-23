package io.aljavap.fillingStation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerPhoneNumber;

    @Column(nullable = false)
    private String companyName;

    // Default constructor (required by JPA)
    public Customer() {
    }

    // Parameterized constructor
    public Customer(String customerName, String customerPhoneNumber, String companyName) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    
}
package com.alcode.az.testmodel.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {


    private String customerName;
    private String customerPhoneNumber;
    private String companyName;

    public Customer() {
    }
    public Customer(String customerName, String customerPhoneNumber, String companyName) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}

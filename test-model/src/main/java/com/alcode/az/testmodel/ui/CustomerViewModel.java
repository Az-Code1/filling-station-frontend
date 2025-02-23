package com.alcode.az.testmodel.ui;

import com.alcode.az.testmodel.entity.Customer;
import com.alcode.az.testmodel.service.CustomerService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

public class CustomerViewModel {
    @Getter
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final StringProperty customerName = new SimpleStringProperty();
    private final StringProperty customerPhoneNumber = new SimpleStringProperty();
    private final StringProperty companyName = new SimpleStringProperty();

    private final CustomerService customerService = new CustomerService();

    public CustomerViewModel() {
        loadCustomers();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public StringProperty customerPhoneNumberProperty() {
        return customerPhoneNumber;
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public void addCustomer() {
        Customer customer = new Customer(customerName.get(), customerPhoneNumber.get(), companyName.get());
        customerService.addCustomer(customer);
        customers.add(customer);
        clearFields();
    }

    private void loadCustomers() {
        customers.setAll(customerService.getAllCustomers());
    }

    private void clearFields() {
        customerName.set("");
        customerPhoneNumber.set("");
        companyName.set("");
    }
}

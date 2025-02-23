package com.alcode.az.testmodel.controller;

import com.alcode.az.testmodel.entity.Customer;
import com.alcode.az.testmodel.ui.CustomerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerController {
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> customerNameColumn;
    @FXML private TableColumn<Customer, String> customerPhoneNumberColumn;
    @FXML private TableColumn<Customer, String> companyNameColumn;
    @FXML private TextField customerNameField;
    @FXML private TextField customerPhoneNumberField;
    @FXML private TextField companyNameField;

    private final CustomerViewModel viewModel = new CustomerViewModel();

    @FXML
    public void initialize() {
        // Bind TableView columns to Customer properties
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        // Bind TableView items to ViewModel's customers list
        customerTable.setItems(viewModel.getCustomers());

        // Bind input fields to ViewModel properties
        customerNameField.textProperty().bindBidirectional(viewModel.customerNameProperty());
        customerPhoneNumberField.textProperty().bindBidirectional(viewModel.customerPhoneNumberProperty());
        companyNameField.textProperty().bindBidirectional(viewModel.companyNameProperty());
    }

    @FXML
    public void addCustomer() {
        viewModel.addCustomer();
    }
}
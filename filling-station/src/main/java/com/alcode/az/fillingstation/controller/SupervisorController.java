package com.alcode.az.fillingstation.controller;

import com.alcode.az.fillingstation.model.StaffPunctuality;
import com.alcode.az.fillingstation.model.Customer;
import com.alcode.az.fillingstation.service.CustomerServiceClient;
import com.alcode.az.fillingstation.service.TextInputFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lombok.Getter;

import java.net.ConnectException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.alcode.az.fillingstation.service.CustomerServiceClient.jsonToCustomersList;

public class SupervisorController {


    @FXML
    private ChoiceBox<String> addNewBookingCompanyNameCombo;
    @FXML
    private TextField addNewBookingQuantityField;
    @FXML
    private Button addNewBookingButton;
    @FXML
    private TextField addNewBookingAmountField;
    @FXML
    private TextField addNewBookingPriceField;
    @FXML
    private TextField addNewOrderAmountField;
    @FXML
    private ComboBox<ObservableList<String>> selectCustomerCombo;
    @FXML
    private ChoiceBox<ObservableList<String>> newBookingCompanyNameCombo;
    @FXML
    private TextField addNewOrderPriceField;
    @FXML
    private TextField addNewOrderQuantityField;
    @FXML
    private ChoiceBox<ObservableList<String>> orderCompanyNameCombo;
    @FXML
    private Button addNewOrderButton;
    @FXML
    private TextField addNewOrderContainerTypeField;
    @FXML
    private TextField addNewOrderContainerIdentityField;
    @FXML
    private TextField createPhoneNumberField;
    @FXML
    private TextField createCustomerNameField;
    @FXML
    private Button createCustomerButton;
    @FXML
    private TextField createCompanyNameField;
    @FXML
    private TableColumn<Customer, String> customerListNameColumn;
    @FXML
    private TableColumn<Customer, String> customerListCompanyColumn;
    @FXML
    private TableColumn<Customer, String> customerListPhoneNumber;
    @FXML
    private TableView<Customer> customerListTable;
    @FXML
    private Button injectIntoCashDrop;
    @FXML
    private Label totalTransferDropLabel;
    @FXML
    private Label totalCashDropLabel;
    @FXML
    private Label totalCashLabel;
    @FXML
    private TextField denomination1000;
    @FXML
    private TextField denomination500;
    @FXML
    private TextField denomination200;
    @FXML
    private TextField denomination100;
    @FXML
    private TextField denomination50;
    @FXML
    private TextField denomination20;
    @FXML
    private TextField denomination10;
    @FXML
    private TextField denomination5;
    @FXML
    private ComboBox<String> cashierComboBox;
    @FXML
    private TableView<CashDrop> cashDropTable;
    @FXML
    private ChoiceBox<String> dropTypeChoiceBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private Text createCustomerResponseText;
    @FXML
    private TextField fingerprintField;
    @FXML
    private TableView<StaffPunctuality> staffTable;
    @FXML
    private TableColumn<StaffPunctuality, String> staffIdColumn;
    @FXML
    private TableColumn<StaffPunctuality, String> nameColumn;
    @FXML
    private ComboBox<StaffPunctuality> staffComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableView<StaffPunctuality> punctualityTable;
    @FXML
    private TableColumn<StaffPunctuality, LocalDate> dateColumn;
    @FXML
    private TableColumn<StaffPunctuality, LocalTime> timeInColumn;
    @FXML
    private TableColumn<StaffPunctuality, LocalTime> timeOutColumn;
    @FXML
    private TableColumn<StaffPunctuality, String> statusColumn;

    private final ObservableList<String> cashiers = FXCollections.observableArrayList("Cashier 1", "Cashier 2");
    private final Map<String, ObservableList<CashDrop>> cashierDrops = new HashMap<>();
    private final ObservableList<CashDrop> currentDrops = FXCollections.observableArrayList();

    // Data Storage
    private final ObservableList<StaffPunctuality> staffList = FXCollections.observableArrayList();
    private final Map<String, ObservableList<StaffPunctuality>> attendanceRecords = new HashMap<>();
    @FXML
    private Text addNewOrderResponseText;
    @FXML
    private Text addNewBookingResponseText;

    @FXML
    public void initialize() {
        filterNumberFields(denomination5, denomination10, denomination20, denomination50,
                denomination100, denomination200, denomination500, denomination1000, amountTextField);
        // Initialize cashiers and their respective drop lists
        for (String cashier : cashiers) {
            cashierDrops.put(cashier, FXCollections.observableArrayList());
        }

        cashierComboBox.setItems(cashiers);
        cashDropTable.setItems(currentDrops);

        // Initialize the drop type choice box
        dropTypeChoiceBox.setItems(FXCollections.observableArrayList("Cash", "Transfer", "Expenses"));

        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set up punctuality table columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeInColumn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        timeOutColumn.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Populate staff list (for demonstration)
        staffList.add(new StaffPunctuality("001", "John Doe"));
        staffList.add(new StaffPunctuality("002", "Jane Smith"));
        staffTable.setItems(staffList);

        // Populate staff combo box
        staffComboBox.setItems(staffList);

        setCustomerListTableColumn();
        populateCustomerListTable();
    }

    @FXML
    private void onCreateNewCashier() {
        String newCashier = "Cashier " + (cashiers.size() + 1);
        cashiers.add(newCashier);
        cashierDrops.put(newCashier, FXCollections.observableArrayList());
        cashierComboBox.setValue(newCashier);
    }

    @FXML
    private void onAddCashDrop() {
        String cashier = cashierComboBox.getValue();
        String type = dropTypeChoiceBox.getValue();
        String amountText = amountTextField.getText().replaceAll(",", "");

        if (cashier == null || type == null || amountText.isEmpty()) {
            showAlert("Error", "Please fill in all fields before adding a cash drop.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            CashDrop newDrop = new CashDrop(type, amount, timestamp);

            cashierDrops.get(cashier).add(newDrop); // Add the drop to the respective cashier's list
            if (cashier.equals(cashierComboBox.getValue())) {
                currentDrops.add(newDrop); // Update the table if the current cashier is selected
            }

            amountTextField.clear();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid amount.");
        }
    }

    @FXML
    private void onSelectCashier(ActionEvent actionEvent) {
        String selectedCashier = cashierComboBox.getValue();
        if (selectedCashier != null) {
            currentDrops.setAll(cashierDrops.get(selectedCashier)); // Display drops for the selected cashier
        }
    }

    @FXML
    private void onCalculateTotalCash(ActionEvent actionEvent) {
        totalCashLabel.setText("Total: ₦" + getTotalCash()+ ".00");
    }

    @FXML
    private void onInjectIntoCashDrop() {
        amountTextField.setText(totalCashLabel.getText().replace("Total: ₦", ""));
    }

    private int parseTextField(TextField textField) {
        String text = textField.getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void markAttendance(ActionEvent actionEvent) {
    }

    @FXML
    public void fetchStaffDetails(ActionEvent actionEvent) {
    }

    @FXML
    public void onShowPunctuality(ActionEvent actionEvent) {

    }
    @FXML
    public void onCreateCustomerButton(ActionEvent actionEvent) {
        String customerName = createCustomerNameField.getText();
        String customerPhoneNumber = createPhoneNumberField.getText();
        String companyName = createCompanyNameField.getText();
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerName);
        newCustomer.setCustomerPhoneNumber(customerPhoneNumber);
        newCustomer.setCompanyName(companyName);

        try {
            String json = CustomerServiceClient.convertArrayListToJson(String.valueOf(FXCollections.observableArrayList(newCustomer)));
//            String json = String.format("{\"customerName\":\"%s\",\"customerPhoneNumber\":\"%s\",\"companyName\":\"%s\"}",
//                    customerName, customerPhoneNumber, companyName);
            CustomerServiceClient.createCustomer(json);
            customerListTable.getItems().add(new Customer( customerName, customerPhoneNumber, companyName));
//            populateCustomerListTable();
            createCustomerResponseText.setFill(javafx.scene.paint.Color.GREEN);
            createCustomerResponseText.setText("Customer added successfully.");
            TextInputFormatter.clearTextFields(createCustomerNameField, createPhoneNumberField, createCompanyNameField);
        } catch (ConnectException e) {
            showAlert("Error", "Failed to connect to the server.");
        } catch (Exception e) {
            showAlert("Error", "Failed to create customer.");
        }

    }
    @FXML
    public void onCustomerLoggTab(Event event) {

        TextInputFormatter.validateTextFieldsMinimum(createCustomerButton, createCustomerResponseText,
                "Please fill in all fields with valid data before adding a customer.",
                createCustomerNameField, createPhoneNumberField, createCompanyNameField);
        TextInputFormatter.phoneNumberFilter(createPhoneNumberField);

        TextInputFormatter.validateTextFieldsMinimum(addNewOrderButton, addNewOrderResponseText,
                "Please fill in all fields with valid data before adding a customer.",
                 addNewOrderPriceField, addNewOrderQuantityField, addNewOrderContainerTypeField, addNewOrderContainerIdentityField);
        TextInputFormatter.numberFilter(addNewOrderPriceField, addNewOrderQuantityField);

        TextInputFormatter.validateTextFieldsMinimum(addNewBookingButton, addNewBookingResponseText,
                "Please fill in all fields with valid data before adding a customer.",
                 addNewBookingPriceField, addNewBookingQuantityField);
    }
    @FXML
    public void onAddNewOrderButton(ActionEvent actionEvent) {
    }
    @FXML
    public void onAdNewBookingButton(ActionEvent actionEvent) {
    }

    // CashDrop class to represent cash drop entries
    @Getter
    public static class CashDrop {
        private final String type;
        private final double amount;
        private final String timestamp;

        public CashDrop(String type, double amount, String timestamp) {
            this.type = type;
            this.amount = amount;
            this.timestamp = timestamp;
        }

    }

    @FXML
    private void fetchStaffDetails() {
        String fingerprintId = fingerprintField.getText();
        for (StaffPunctuality staff : staffList) {
            if (staff.getStaffId().equals(fingerprintId)) {
                staffTable.getSelectionModel().select(staff);
                break;
            }
        }
    }

    // Mark Attendance
    @FXML
    private void markAttendance() {
        StaffPunctuality selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();

            // Create a new attendance record
            StaffPunctuality attendanceRecord = new StaffPunctuality(selectedStaff.getStaffId(), selectedStaff.getName());
            attendanceRecord.setDate(currentDate);
            attendanceRecord.setTimeIn(currentTime);
            attendanceRecord.setStatus("Punctual"); // Default status

            // Add to attendance records
            ObservableList<StaffPunctuality> records = attendanceRecords.getOrDefault(selectedStaff.getStaffId(), FXCollections.observableArrayList());
            records.add(attendanceRecord);
            attendanceRecords.put(selectedStaff.getStaffId(), records);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Attendance marked for " + selectedStaff.getName());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No staff member selected!");
            alert.showAndWait();
        }
    }

    // Show Punctuality
    @FXML
    private void onShowPunctuality() {
        StaffPunctuality selectedStaff = staffComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (selectedStaff != null && startDate != null && endDate != null) {
            ObservableList<StaffPunctuality> records = attendanceRecords.get(selectedStaff.getStaffId());
            if (records != null) {
                ObservableList<StaffPunctuality> filteredRecords = FXCollections.observableArrayList();
                for (StaffPunctuality record : records) {
                    if (!record.getDate().isBefore(startDate) && !record.getDate().isAfter(endDate)) {
                        filteredRecords.add(record);
                    }
                }
                punctualityTable.setItems(filteredRecords);
            } else {
                punctualityTable.setItems(FXCollections.observableArrayList());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a staff member and date range!");
            alert.showAndWait();
        }
    }
    private void filterNumberFields(TextField... textField) {
        for (TextField field : textField) {
            TextInputFormatter.numberFilter(field);
            numberFormatWithComma(field);
        }
    }
    public void numberFormatWithComma(TextField textField) {

        // Decimal format to add comma as separator
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Listener for changes in the text field
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Only process if the new value is a valid number
            if (newValue.matches("\\d*")) {
                try {

                    String formatted = decimalFormat.format(getTotalCash());
                    // Update the text and set caret at the end
                    totalCashLabel.setText("Total: ₦" + formatted+ ".00");
                    // Ensure the caret remains at the end of the text
                } catch (NumberFormatException e) {
                    // Handle invalid number input gracefully (optional)
                    textField.setPromptText("Enter a valid value"); // or previous value if necessary
                }
            } else {
                // If the new value is invalid (non-numeric), revert to old value
                textField.setText(oldValue);
            }
        });
    }
    private int getTotalCash(){

        try {
            int count1000 = parseTextField(denomination1000);
            int count500 = parseTextField(denomination500);
            int count200 = parseTextField(denomination200);
            int count100 = parseTextField(denomination100);
            int count50 = parseTextField(denomination50);
            int count20 = parseTextField(denomination20);
            int count10 = parseTextField(denomination10);
            int count5 = parseTextField(denomination5);

            return (count1000 * 1000) + (count500 * 500) + (count200 * 200) + (count100 * 100) +
                    (count50 * 50) + (count20 * 20) + (count10 * 10) + (count5 * 5);
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid counts for denominations.");
        }
        return 0;
    }

    private void populateCustomerListTable() {
        // Populate the table with database data
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            customers.addAll(jsonToCustomersList(CustomerServiceClient.getAllCustomers()));
            customerListTable.getItems().addAll(customers);
        } catch (ConnectException e) {
            showAlert("Error", "Failed to connect to the server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setCustomerListTableColumn(){
        customerListNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerListCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        customerListPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
    }
}

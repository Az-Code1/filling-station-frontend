package com.alcode.az.fillingstation.controller;

import com.alcode.az.fillingstation.model.StaffPunctuality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class AttendanceController {

    // FXML Components
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

    // Data Storage
    private ObservableList<StaffPunctuality> staffList = FXCollections.observableArrayList();
    private Map<String, ObservableList<StaffPunctuality>> attendanceRecords = new HashMap<>();

    // Initialize Method
    @FXML
    public void initialize() {
        // Set up staff table columns
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
    }

    // Fetch Staff Details
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
    private void showPunctuality() {
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
}
package com.alcode.az.fillingstation.controller;

import com.alcode.az.fillingstation.model.Address;
import com.alcode.az.fillingstation.model.Guarantor;
import com.alcode.az.fillingstation.model.Staff;
import com.alcode.az.fillingstation.service.TextInputFormatter;
import com.alcode.az.fillingstation.viewmodel.StaffViewModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StaffController implements Initializable {

    @FXML private Tab addNewStaffTab;
    @FXML private Button clearAllFieldButton;
    @FXML private TableColumn<?, ?> durationColumn;

    @FXML private Button editStaffDetailsButton;
    @FXML private TableColumn<Staff, String> firstNameColumn;

    @FXML private DatePicker guarantor1DOBPicker;
    @FXML private RadioButton guarantor1FemaleRadio;

    @FXML private TextField guarantor1FirstNameField;
    @FXML private TextArea guarantor1HomeTextArea;
    @FXML private RadioButton guarantor1MaleRadio;

    @FXML private TextField guarantor1NINField;
    @FXML private TextField guarantor1OccupationField;
    @FXML private TextField guarantor1OthernameField;

    @FXML private TextField guarantor1RelationshipField;
    @FXML private TextField guarantor1SurnameField;

    @FXML private ToggleGroup guarantor1genderGroup;
    @FXML private TextField guarantor1phoneField;
    @FXML private DatePicker guarantor2DOBPicker;

    @FXML private RadioButton guarantor2FemaleRadio;
    @FXML private TextField guarantor2FirstNameField;
    @FXML private TextArea guarantor2HomeTextArea;

    @FXML private RadioButton guarantor2MaleRadio;
    @FXML private TextField guarantor2NINField;

    @FXML private TextField guarantor2OccupationField;
    @FXML private TextField guarantor2OthernameField;

    @FXML private TextField guarantor2RelationshipField;
    @FXML private TextField guarantor2SurnameField;
    @FXML private ToggleGroup guarantor2genderGroup;

    @FXML private TextField guarantor2phoneField;
    @FXML private TableColumn<Staff, String> phoneNumberColumn;
    @FXML private ChoiceBox<?> salaryChioceBox;

    @FXML private TextField salaryTextField;
    @FXML private Button saveStaffInfoButton;

    @FXML private Text saveStaffResponseText;
    @FXML private TableColumn<?, String> serialNoColumn;

    @FXML private ComboBox<String> staffStateCombo;
    @FXML private TextField staffCityField;

    @FXML private DatePicker staffDOBPicker;
    @FXML private RadioButton staffFemaleRadio;

    @FXML private TextField staffFirstNameField;
    @FXML private ToggleGroup staffGenderGroup;

    @FXML private ImageView staffImageView;
    @FXML private RadioButton staffMaleRadio;

    @FXML private TextField staffNINField;
    @FXML private TextField staffOthernameField;

    @FXML private TextField staffPhoneNumberField;
    @FXML private TableColumn<Staff, String> staffRoleColumn;

    @FXML private TextField staffSecondNumberField;
    @FXML private TextArea staffStreetAddressArea;

    @FXML private TextField staffSurnameField;
    @FXML private TableView<Staff> staffTable;

    @FXML private TableColumn<Staff, String> streetAddressColumn;
    @FXML private TableColumn<Staff, String> surnameColumn;

    @FXML private Button takePhotoButton;
    @FXML private Button validateGuarantor1NINButton;

    @FXML private Button validateGuarantor2NINButton;
    @FXML private Button validateStaffNINButton;

    private final StaffViewModel viewModel = new StaffViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind TableView columns
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().staffSurnameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().staffFirstNameProperty());
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().staffPhoneNumberProperty());
//        streetAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getStaffAddress().streetProperty());
//        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getStaffAddress().cityProperty());
//        streetAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getStaffAddress().stateProperty());
        // Bind TableView to ViewModel
        staffTable.setItems(viewModel.getStaffList());

        // Handle row selection
        staffTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
}

    private void fillFormWithSelectedRow() {
        Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            staffSurnameField.setText(selectedStaff.getStaffSurname());
            staffFirstNameField.setText(selectedStaff.getStaffFirstName());
            staffOthernameField.setText(selectedStaff.getStaffOtherName());
            staffDOBPicker.setValue(selectedStaff.getStaffDateOfBirth());
            staffPhoneNumberField.setText(selectedStaff.getStaffPhoneNumber());
            if (selectedStaff.getStaffGender().equalsIgnoreCase("Male")) {
                staffMaleRadio.setSelected(true);
            } else {
                staffFemaleRadio.setSelected(true);
            }
            staffSecondNumberField.setText(selectedStaff.getSecondPhoneNumber());
            staffCityField.setText(selectedStaff.getStaffAddress().getCity());
            staffStreetAddressArea.setText(selectedStaff.getStaffAddress().getStreet());
            staffNINField.setText(selectedStaff.getStaffNIN());
            staffStateCombo.setValue(selectedStaff.getStaffAddress().getState());

            guarantor1SurnameField.setText(selectedStaff.getGuarantor1().getGuarantorSurname());
            guarantor1FirstNameField.setText(selectedStaff.getGuarantor1().getGuarantorFirstName());
            guarantor1OthernameField.setText(selectedStaff.getGuarantor1().getGuarantorOtherName());
            guarantor1DOBPicker.setValue(selectedStaff.getGuarantor1().getGuarantorDateOfBirth());
            guarantor1phoneField.setText(selectedStaff.getGuarantor1().getGuarantorPhoneNumber());
            guarantor1HomeTextArea.setText(selectedStaff.getGuarantor1().getGuarantorAddress().getStreet());
            guarantor1NINField.setText(selectedStaff.getGuarantor1().getGuarantorNIN());
            guarantor1OccupationField.setText(selectedStaff.getGuarantor1().getGuarantorOccupation());
            guarantor1RelationshipField.setText(selectedStaff.getGuarantor1().getGuarantorRelationship());
            if (selectedStaff.getGuarantor1().getGuarantorGender().equalsIgnoreCase("Male")) {
                guarantor1MaleRadio.setSelected(true);
            } else {
                guarantor1FemaleRadio.setSelected(true);
            }

            guarantor2SurnameField.setText(selectedStaff.getGuarantor2().getGuarantorSurname());
            guarantor2FirstNameField.setText(selectedStaff.getGuarantor2().getGuarantorFirstName());
            guarantor2OthernameField.setText(selectedStaff.getGuarantor2().getGuarantorOtherName());
            guarantor2DOBPicker.setValue(selectedStaff.getGuarantor2().getGuarantorDateOfBirth());
            guarantor2phoneField.setText(selectedStaff.getGuarantor2().getGuarantorPhoneNumber());
            guarantor2HomeTextArea.setText(selectedStaff.getGuarantor2().getGuarantorAddress().getStreet());
            guarantor2NINField.setText(selectedStaff.getGuarantor2().getGuarantorNIN());
            guarantor2OccupationField.setText(selectedStaff.getGuarantor2().getGuarantorOccupation());
            guarantor2RelationshipField.setText(selectedStaff.getGuarantor2().getGuarantorRelationship());
            if (selectedStaff.getGuarantor2().getGuarantorGender().equalsIgnoreCase("Male")) {
                guarantor2MaleRadio.setSelected(true);
            } else {
                guarantor2FemaleRadio.setSelected(true);
            }

//            cmbRole.setValue(selectedStaff.getRole());
        }
    }
    @FXML void staffTableMouseEntered(){
    }

    @FXML void staffTableMouseClicked(){
        fillFormWithSelectedRow();
    }
    @FXML void staffTableKeyTyped(){
    }
    @FXML void staffTableKeyReleased(){
        fillFormWithSelectedRow();
    }
    @FXML
    void onAddNewStaffTab(Event event) {

    }
    @FXML
    void onEditStaffDetailsButton(ActionEvent event) {
        handleUpdate();
    }
    @FXML
    void onClearAllFieldButton(ActionEvent event) {
        clearFields();
    }

    @FXML
    void onGuarantor1FemaleRadio(ActionEvent event) {

    }

    @FXML
    void onGuarantor1MaleRadio(ActionEvent event) {

    }

    @FXML
    void onGuarantor2FemaleRadio(ActionEvent event) {

    }

    @FXML
    void onGuarantor2MaleRadio(ActionEvent event) {

    }

    @FXML
    void onSaveStaffInfoButton(ActionEvent event) {
        System.out.println("Save button clicked");
        handleSave();
        System.out.println("Staff information updated");
    }

    @FXML
    void onStaffFemaleRadio(ActionEvent event) {

    }

    @FXML
    void onStaffMaleRadio(ActionEvent event) {

    }


    @FXML
    void onTakePhotoButton(ActionEvent event) {

    }

    @FXML
    void onValidateGuarantor2NINButton(ActionEvent event) {

    }

    @FXML
    void onValidateStaffNINButton(ActionEvent event) {

    }
    private void populateFields(Staff staff) {
        staffSurnameField.setText(staff.getStaffSurname());
        staffFirstNameField.setText(staff.getStaffFirstName());
        staffOthernameField.setText(staff.getStaffOtherName());
        staffPhoneNumberField.setText(staff.getStaffPhoneNumber());
        staffSecondNumberField.setText(staff.getSecondPhoneNumber());
        staffNINField.setText(staff.getStaffNIN());
        staffDOBPicker.setValue(staff.getStaffDateOfBirth());
        if (staff.getStaffGender().equalsIgnoreCase("Male")) {
            staffMaleRadio.setSelected(true);
        } else {
            staffFemaleRadio.setSelected(true);
        }

        Address address = staff.getStaffAddress();
        staffStreetAddressArea.setText(address.getStreet());
        staffCityField.setText(address.getCity());
        staffStateCombo.setValue(address.getState());

        Guarantor guarantor1 = staff.getGuarantor1();
        if (guarantor1 != null) {
            guarantor1SurnameField.setText(guarantor1.getGuarantorSurname());
            guarantor1FirstNameField.setText(guarantor1.getGuarantorFirstName());
            guarantor1OthernameField.setText(guarantor1.getGuarantorOtherName());
            guarantor1phoneField.setText(guarantor1.getGuarantorPhoneNumber());
            guarantor1OccupationField.setText(guarantor1.getGuarantorOccupation());
            guarantor1NINField.setText(guarantor1.getGuarantorNIN());
            guarantor1DOBPicker.setValue(guarantor1.getGuarantorDateOfBirth());
        }

        Guarantor guarantor2 = staff.getGuarantor2();
        if (guarantor2 != null) {
            guarantor2SurnameField.setText(guarantor2.getGuarantorSurname());
            guarantor2FirstNameField.setText(guarantor2.getGuarantorFirstName());
            guarantor2OthernameField.setText(guarantor2.getGuarantorOtherName());
            guarantor2phoneField.setText(guarantor2.getGuarantorPhoneNumber());
            guarantor2OccupationField.setText(guarantor2.getGuarantorOccupation());
            guarantor2NINField.setText(guarantor2.getGuarantorNIN());
            guarantor2DOBPicker.setValue(guarantor2.getGuarantorDateOfBirth());
        }
    }

    private void handleSave() {
        Staff staff = createStaffFromFields();
        System.out.println("About to save staff information and staff.getStaffId() returns "
                + staff.getStaffId());
        viewModel.saveStaff(staff);
        System.out.println("\nInside handleSave method Staff information saved if there are no errors");
        clearFields();
    }

    private void handleUpdate() {
        Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            Staff updatedStaff = createStaffFromFields();
            updatedStaff.setStaffId(selectedStaff.getStaffId());
            viewModel.updateStaff(updatedStaff);
        }
    }

    private void handleDelete() {
        Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            viewModel.deleteStaff(selectedStaff.getStaffId());
        }
    }


    private Staff createStaffFromFields() {
        Staff staff = new Staff();
        staff.setStaffSurname(staffSurnameField.getText());
        staff.setStaffFirstName(staffFirstNameField.getText());
        staff.setStaffOtherName(staffOthernameField.getText());
        staff.setStaffPhoneNumber(staffPhoneNumberField.getText());
        staff.setSecondPhoneNumber(staffSecondNumberField.getText());
        staff.setStaffNIN(staffNINField.getText());
        staff.setStaffDateOfBirth(toJSonLocalDate(staffDOBPicker));
        if (staffMaleRadio.isSelected()) {
            staff.setStaffGender("Male");
        } else {
            staff.setStaffGender("Female");
        }

        final Address address = getAddress();
        staff.setStaffAddress(address);

        final Guarantor guarantor1 = getGuarantor1();
        staff.setGuarantor1(guarantor1);

        final Guarantor guarantor2 = getGuarantor2();
        staff.setGuarantor2(guarantor2);
        System.out.println();
        return staff;
    }

    @NotNull
    private Address getAddress() {
        Address address = new Address();
        address.setStreet(staffStreetAddressArea.getText());
        address.setCity(staffCityField.getText());
        address.setState(staffStateCombo.getValue());
        return address;
    }
    @NotNull
    private Guarantor getGuarantor1() {
        Guarantor guarantor1 = new Guarantor();
        guarantor1.setGuarantorSurname(guarantor1SurnameField.getText());
        guarantor1.setGuarantorFirstName(guarantor1FirstNameField.getText());
        guarantor1.setGuarantorOtherName(guarantor1OthernameField.getText());
        guarantor1.setGuarantorPhoneNumber(guarantor1phoneField.getText());
        guarantor1.setGuarantorOccupation(guarantor1OccupationField.getText());
        guarantor1.setGuarantorNIN(guarantor1NINField.getText());
        guarantor1.setGuarantorDateOfBirth(guarantor1DOBPicker.getValue());
        guarantor1.setGuarantorAddress(getGuarantor1Address());

        return guarantor1;
    }
    @NotNull
    private Address getGuarantor1Address() {
        Address address = new Address();
        address.setStreet(guarantor1HomeTextArea.getText());
        address.setCity(staffCityField.getText());
        address.setState(staffStateCombo.getValue());
        return address;
    }

    @NotNull
    private Guarantor getGuarantor2() {
        Guarantor guarantor2 = new Guarantor();
        guarantor2.setGuarantorSurname(guarantor2SurnameField.getText());
        guarantor2.setGuarantorFirstName(guarantor2FirstNameField.getText());
        guarantor2.setGuarantorOtherName(guarantor2OthernameField.getText());
        guarantor2.setGuarantorPhoneNumber(guarantor2phoneField.getText());
        guarantor2.setGuarantorOccupation(guarantor2OccupationField.getText());
        guarantor2.setGuarantorNIN(guarantor2NINField.getText());
        guarantor2.setGuarantorDateOfBirth(guarantor2DOBPicker.getValue());
        guarantor2.setGuarantorAddress(getGuarantor2Address());
        return guarantor2;
    }
    @NotNull
    private Address getGuarantor2Address() {
        Address address = new Address();
        address.setStreet(guarantor2HomeTextArea.getText());
        address.setCity(staffCityField.getText());
        address.setState(staffStateCombo.getValue());
        return address;
    }

    private void clearFields() {

        TextInputFormatter.clearTextFields(staffSurnameField, staffFirstNameField, staffOthernameField,
                staffPhoneNumberField, staffSecondNumberField, staffCityField, staffStreetAddressArea,
                guarantor1SurnameField, guarantor1FirstNameField, guarantor1OthernameField, guarantor1phoneField,
                guarantor1HomeTextArea, guarantor1NINField, guarantor1OccupationField, guarantor1RelationshipField,
                guarantor2SurnameField, guarantor2FirstNameField, guarantor2OthernameField, staffNINField,
                guarantor2phoneField, guarantor2HomeTextArea, guarantor2NINField, guarantor2OccupationField,
                guarantor2RelationshipField );
        staffDOBPicker.setValue(null);
        staffMaleRadio.setSelected(true);
        staffFemaleRadio.setSelected(false);
        guarantor1MaleRadio.setSelected(true);
        guarantor1FemaleRadio.setSelected(false);
        guarantor2MaleRadio.setSelected(true);
        guarantor2FemaleRadio.setSelected(false);
        staffStateCombo.setValue(null);
        guarantor1DOBPicker.setValue(null);
        guarantor2DOBPicker.setValue(null);
    }
    private LocalDate toJSonLocalDate(DatePicker datePicker) {
        LocalDate parsedLocalDate;
       // Convert LocalDate to yyyy-mm-dd format string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateString = datePicker.getValue().format(formatter);

        // Parse the formatted string back into a LocalDate
        parsedLocalDate = LocalDate.parse(formattedDateString, formatter);

        System.out.println("Original LocalDate: " + datePicker.getValue());
        System.out.println("Parsed LocalDate (yyyy-mm-dd): " + parsedLocalDate); // This will look the same
        return parsedLocalDate;
    }
}
package com.alcode.az.fillingstation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewStaffController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button clearAllFieldButton;

    @FXML
    private DatePicker guarantor1DOBPicker;

    @FXML
    private RadioButton guarantor1FemaleRadio;

    @FXML
    private TextField guarantor1FirstNameField;

    @FXML
    private TextArea guarantor1HomeTextArea;

    @FXML
    private RadioButton guarantor1MaleRadio;

    @FXML
    private TextField guarantor1NINField;

    @FXML
    private TextField guarantor1OccupationField;

    @FXML
    private TextField guarantor1OthernameField;

    @FXML
    private TextField guarantor1RelationshipField;

    @FXML
    private TextField guarantor1SurnameField;

    @FXML
    private ToggleGroup guarantor1genderGroup;

    @FXML
    private TextField guarantor1phoneField;

    @FXML
    private DatePicker guarantor2DOBPicker;

    @FXML
    private RadioButton guarantor2FemaleRadio;

    @FXML
    private TextField guarantor2FirstNameField;

    @FXML
    private TextArea guarantor2HomeTextArea;

    @FXML
    private RadioButton guarantor2MaleRadio;

    @FXML
    private TextField guarantor2NINField;

    @FXML
    private TextField guarantor2OccupationField;

    @FXML
    private TextField guarantor2OthernameField;

    @FXML
    private TextField guarantor2RelationshipField;

    @FXML
    private TextField guarantor2SurnameField;

    @FXML
    private ToggleGroup guarantor2genderGroup;

    @FXML
    private TextField guarantor2phoneField;

    @FXML
    private Button saveStaffInfoButton;

    @FXML
    private Text saveStaffResponseText;

    @FXML
    private ComboBox<?> staffCityCombo;

    @FXML
    private TextField staffCityField;

    @FXML
    private DatePicker staffDOBPicker;

    @FXML
    private RadioButton staffFemaleRadio;

    @FXML
    private TextField staffFirstName;

    @FXML
    private ToggleGroup staffGenderGroup;

    @FXML
    private ImageView staffImageView;

    @FXML
    private RadioButton staffMaleRadio;

    @FXML
    private TextField staffNINField;

    @FXML
    private TextField staffOthernameField;

    @FXML
    private TextField staffPhoneNumberField;

    @FXML
    private TextField staffSecondNumberField;

    @FXML
    private TextArea staffStreetAddressArea;

    @FXML
    private TextField staffSurname;

    @FXML
    private Button takePhotoButton;

    @FXML
    private Button validateGuarantor1NINButton;

    @FXML
    private Button validateGuarantor2NINButton;

    @FXML
    private Button validateStaffNINButton;

    @FXML
    void onClearAllFieldButton(ActionEvent event) {

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

}


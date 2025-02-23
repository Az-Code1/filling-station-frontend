package com.alcode.az.fillingstation.service;

import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {

    // Example 1: Simple Email Validation
    public boolean validateEmail(TextField emailField) {
        String email = emailField.getText();
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    // Example 2: Show Error on Invalid Email
    public void validateEmailWithError(TextField emailField, Label errorLabel) {
        if (!validateEmail(emailField)) {
            errorLabel.setText("Invalid email address");
        } else {
            errorLabel.setText("");
        }
    }

    // Example 3: Disable Button on Invalid Email
    public void bindEmailValidation(TextField emailField, Button submitButton) {
        emailField.textProperty().addListener((obs, oldVal, newVal) -> {
            submitButton.setDisable(!validateEmail(emailField));
        });
    }

    // Example 1: Password Length Validation
    public boolean validatePasswordLength(PasswordField passwordField, int minLength) {
        return passwordField.getText().length() >= minLength;
    }

    // Example 2: Password Complexity Validation
    public boolean validatePasswordComplexity(PasswordField passwordField) {
        String password = passwordField.getText();
        return password.matches(".*[A-Z].*") && // At least one uppercase
                password.matches(".*[a-z].*") && // At least one lowercase
                password.matches(".*\\d.*");     // At least one digit
    }

    // Example 3: Show Password Strength
    public void showPasswordStrength(PasswordField passwordField, Label strengthLabel) {
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() >= 8 && validatePasswordComplexity(passwordField)) {
                strengthLabel.setText("Strong");
            } else if (newVal.length() >= 6) {
                strengthLabel.setText("Medium");
            } else {
                strengthLabel.setText("Weak");
            }
        });
    }

    // Example 1: Integer Validation
    public boolean validateInteger(TextField numberField) {
        try {
            Integer.parseInt(numberField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Example 2: Positive Number Validation
    public boolean validatePositiveNumber(TextField numberField) {
        try {
            double number = Double.parseDouble(numberField.getText());
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Example 3: Range Validation
    public boolean validateNumberRange(TextField numberField, double min, double max) {
        try {
            double number = Double.parseDouble(numberField.getText());
            return number >= min && number <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Example 1: Date Format Validation
    public boolean validateDateFormat(TextField dateField, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(dateField.getText());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Example 2: Future Date Validation
    public boolean validateFutureDate(TextField dateField, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateField.getText());
            return date.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }

    // Example 3: Past Date Validation
    public boolean validatePastDate(TextField dateField, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateField.getText());
            return date.before(new Date());
        } catch (ParseException e) {
            return false;
        }
    }

    // Example 1: Simple Phone Number Validation
    public boolean validatePhoneNumber(TextField phoneField) {
        return phoneField.getText().matches("\\d{10}");
    }

    // Example 2: International Phone Number Validation
    public boolean validateInternationalPhoneNumber(TextField phoneField) {
        return phoneField.getText().matches("\\+\\d{1,3}\\d{9}");
    }

    // Example 3: Show Error on Invalid Phone Number
    public void validatePhoneNumberWithError(TextField phoneField, Label errorLabel) {
        if (!validatePhoneNumber(phoneField)) {
            errorLabel.setText("Invalid phone number");
        } else {
            errorLabel.setText("");
        }
    }

    // Example 1: Minimum Length Validation
    public boolean validateMinLength(TextField textField, int minLength) {
        return textField.getText().length() >= minLength;
    }

    // Example 2: Maximum Length Validation
    public boolean validateMaxLength(TextField textField, int maxLength) {
        return textField.getText().length() <= maxLength;
    }

    // Example 3: Exact Length Validation
    public boolean validateExactLength(TextField textField, int length) {
        return textField.getText().length() == length;
    }

    // Example 1: Non-Empty ComboBox Validation
    public boolean validateComboBox(ComboBox<String> comboBox) {
        return comboBox.getValue() != null;
    }

    // Example 2: Specific Value Validation
    public boolean validateComboBoxValue(ComboBox<String> comboBox, String expectedValue) {
        return expectedValue.equals(comboBox.getValue());
    }

    // Example 3: Show Error on Invalid Selection
    public void validateComboBoxWithError(ComboBox<String> comboBox, Label errorLabel) {
        if (!validateComboBox(comboBox)) {
            errorLabel.setText("Please select a value");
        } else {
            errorLabel.setText("");
        }
    }

    // Example 1: File Existence Validation
    public boolean validateFileExists(TextField filePathField) {
        return new File(filePathField.getText()).exists();
    }

    // Example 2: File Extension Validation
    public boolean validateFileExtension(TextField filePathField, String extension) {
        return filePathField.getText().endsWith(extension);
    }

    // Example 3: Show Error on Invalid File Path
    public void validateFilePathWithError(TextField filePathField, Label errorLabel) {
        if (!validateFileExists(filePathField)) {
            errorLabel.setText("File does not exist");
        } else {
            errorLabel.setText("");
        }
    }

    // Example 1: Simple URL Validation
    public boolean validateURL(TextField urlField) {
        try {
            new URL(urlField.getText()).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Example 2: Show Error on Invalid URL
    public void validateURLWithError(TextField urlField, Label errorLabel) {
        if (!validateURL(urlField)) {
            errorLabel.setText("Invalid URL");
        } else {
            errorLabel.setText("");
        }
    }

    // Example 3: Disable Button on Invalid URL
    public void bindURLValidation(TextField urlField, Button submitButton) {
        urlField.textProperty().addListener((obs, oldVal, newVal) -> {
            submitButton.setDisable(!validateURL(urlField));
        });
    }

    // Example 1: Custom Regex Validation
    public boolean validateRegex(TextField textField, String regex) {
        return textField.getText().matches(regex);
    }

    // Example 2: Show Error on Regex Mismatch
    public void validateRegexWithError(TextField textField, String regex, Label errorLabel) {
        if (!validateRegex(textField, regex)) {
            errorLabel.setText("Input does not match required format");
        } else {
            errorLabel.setText("");
        }
    }

    // Example 3: Disable Button on Regex Mismatch
    public void bindRegexValidation(TextField textField, String regex, Button submitButton) {
        textField.textProperty().addListener((obs, oldVal, newVal) -> {
            submitButton.setDisable(!validateRegex(textField, regex));
        });
    }

}

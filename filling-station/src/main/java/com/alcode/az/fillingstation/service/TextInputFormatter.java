package com.alcode.az.fillingstation.service;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Text;

public class TextInputFormatter {

    public static void numberFilter(TextField... textFields) {
        for (TextField textField : textFields) {
            TextFormatter<String> numberFilter = new TextFormatter<>(change -> {
                if (change.getText().matches("[0-9]*")) {
                    return change;
                }
                return null; // Reject the change if it's not a number
            });
            // Apply the TextFormatter to the TextField
            textField.setTextFormatter(numberFilter);
        }
    }

    public static void phoneNumberFilter(TextField textField) {
        TextFormatter<String> numberFilter = new TextFormatter<>(change -> {

            if (change.getText().matches("\\+?[0-9]*")) {
                return change;
            }
            return null; // Reject the change if it's not a number
        });
        // Apply the TextFormatter to the TextField
        textField.setTextFormatter(numberFilter);

    }

    public static void validateTextFieldsMinimum(Button button, Text responseText, String text, TextField... textFields) {

        try {
            isTextValid(button, textFields);
            for (TextField field : textFields) {
                button.setOnMouseEntered(event -> {
                    setButtonState(button, responseText, text, textFields);
                });
                field.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (newValue) {
                        responseText.setFill(javafx.scene.paint.Color.RED);
                        button.setDisable(true); // Disable the button
                        responseText.setText(text);
                    } else {
                        button.setDisable(false); // Enable the button
                        responseText.setText(""); // Clear error text
                    }
                    setButtonState(button, responseText, text, textFields);
                });
                field.setOnKeyTyped(event -> {
                    setButtonState(button, responseText, text, textFields);
                });
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isTextValid(Button button, TextField[] textFields) {
        for (TextField field : textFields) {
            if (field.getText().trim().length() <= 2) {
                button.setDisable(true);
                return false;
            }
        }
        button.setDisable(false);
        return true;
    }
    private static void setButtonState(Button button, Text responseText, String text, TextField... textFields) {
        if (!isTextValid(button, textFields)) {
            button.setDisable(true); // Disable the button
            responseText.setText(text);
        } else {
            button.setDisable(false); // Enable the button
            responseText.setText(""); // Clear error text
        }
    }

    public static String formatName(String name) {
        return name.trim().replaceAll("\\s+", " ");
    }

    public static String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.trim().replaceAll("\\s+", "");
    }

    public static String formatEmail(String email) {
        return email.trim().replaceAll("\\s+", "");
    }

    public static String formatAddress(String address) {
        return address.trim().replaceAll("\\s+", " ");
    }

    public static String formatPassword(String password) {
        return password.trim();
    }

    public static String formatUsername(String username) {
        return username.trim().replaceAll("\\s+", "");
    }

    public static String formatAmount(String amount) {
        return amount.trim().replaceAll("\\s+", "");
    }

    public static String formatProduct(String product) {
        return product.trim().replaceAll("\\s+", " ");
    }

    public static String formatQuantity(String quantity) {
        return quantity.trim().replaceAll("\\s+", "");
    }

    public static String formatPrice(String price) {
        return price.trim().replaceAll("\\s+", "");
    }

    public static String formatDate(String date) {
        return date.trim().replaceAll("\\s+", "");
    }

    public static String formatTime(String time) {
        return time.trim().replaceAll("\\s+", "");
    }

    public static String formatRole(String role) {
        return role.trim().replaceAll("\\s+", " ");
    }

    public static String formatStatus(String status) {
        return status.trim().replaceAll("\\s+", " ");
    }

    public static String formatDescription(String description) {
        return description.trim().replaceAll("\\s+", " ");
    }

    public static String formatAccountType(String accountType) {
        return accountType.trim().replaceAll("\\s+", " ");
    }

    public static String formatAccountNumber(String accountNumber) {
        return accountNumber.trim().replaceAll("\\s+", "");
    }

    public static String formatAccountName(String accountName) {

        return accountName.trim().replaceAll("\\s+", " ");
    }

    public static String formatBankName(String bankName) {
        return bankName.trim().replaceAll("\\s+", " ");
    }

    public static String formatBranchName(String branchName) {
        return branchName.trim().replaceAll("\\s+", " ");
    }

    public static String formatAccountBalance(String accountBalance) {
        return accountBalance.trim().replaceAll("\\s+", "");
    }

    public static String formatAccountStatus(String accountStatus) {
        return accountStatus.trim().replaceAll("\\s+", " ");
    }

    public static void clearTextFields(TextInputControl... textInputControl) {
        for(TextInputControl textField : textInputControl){
            textField.clear();
        }
    }
}

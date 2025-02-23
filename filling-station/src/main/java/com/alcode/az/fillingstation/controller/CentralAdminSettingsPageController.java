package com.alcode.az.fillingstation.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CentralAdminSettingsPageController implements Initializable {

    @FXML
    private StackPane passwordStack;
    private CustomToggleButton customToggleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        passwordStack.getChildren().add(getCustomToggleButton());
        customToggleListener();
    }

    StackPane getCustomToggleButton(){

        customToggleButton = new CustomToggleButton();
        return customToggleButton.getToggleStackPane();
    }

    void customToggleListener(){

        customToggleButton.getThumb().translateXProperty().addListener((observable, oldValue, newValue) -> {

            if (customToggleButton.getThumb().getTranslateX() == 15) {
                toggleIsSelected();
            } else if (customToggleButton.getThumb().getTranslateX() == -15){
                toggleIsNotSelected();
            }
        });
    }
    void toggleIsSelected(){

        System.out.println("Inside toggleIsSelected method");
    }
    void toggleIsNotSelected(){

        System.out.println("Inside toggleIsNotSelected method");
    }

    void setTextFieldEditable(boolean editable, TextField... textField){

        for (TextField field : textField) {
            field.setEditable(editable);
        }
    }
}

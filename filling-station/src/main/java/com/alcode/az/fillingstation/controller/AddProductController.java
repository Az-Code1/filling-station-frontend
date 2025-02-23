package com.alcode.az.fillingstation.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class AddProductController implements Initializable{
    @FXML
    private TitledPane productTitlePane;

    @FXML
    private VBox productVBox;

    @FXML
    private HBox nuzzleHBox;

    @FXML
    private VBox nuzzleVBox;

    @FXML
    private HBox pricesHBox;

    @FXML
    private Spinner<Integer> priceSpinner;

    private int noOfPrices;
    public HBox getPricesHBox() {
        return pricesHBox;
    }

    public Spinner<Integer> getPriceSpinner() {
        return priceSpinner;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1); // min, max, initial value
        priceSpinner.setValueFactory(valueFactory);
        priceSpinner.setEditable(false);

        priceSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setNoOfPrices(newValue);
            }
        });
    }

    private void setNoOfPrices(Integer newValue) {
    }

    int getNoOfPrices(){

        noOfPrices = priceSpinner.getValue();
        return noOfPrices;
    }
}
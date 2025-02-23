package com.alcode.az.fillingstation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BranchAccountController implements Initializable {
    @FXML
    private VBox branchAccountVBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addProduct("DPK");
        addProduct("AGO");
        addProduct("PMS");
    }

    void addProduct(String productName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/add-product-view.fxml"));
            TitledPane root = loader.load();
            root.setText(productName);
            AddProductController addProductController = loader.getController();
            addProductController.getPriceSpinner().valueProperty().addListener((observable, oldValue, newValue) -> {

                if (newValue > oldValue && newValue <= 5) {

                        addPrices(addProductController.getPricesHBox());
                } else {
                    if(oldValue > newValue && newValue >= 1){
//                        addProductController.getPricesHBox().getChildren().removeLast();
                    }
                }
            });

            branchAccountVBox.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addPrices(HBox hBox){

        FXMLLoader priceLoader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/product-price-view.fxml"));
        HBox priceContent = null;
        try {
            priceContent = priceLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (hBox == null) {
            System.out.println("pricesHBox is null! Check FXML and controller setup.");
        } else {
            hBox.getChildren().add(priceContent);
        }

    }

}

package com.alcode.az.demo1;

import javafx.stage.Stage;
import javafx.scene.Scene;
import org.controlsfx.control.CheckComboBox;
import javafx.application.Application;
import javafx.scene.layout.StackPane;


public class CheckComboBoxExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        CheckComboBox<String> checkComboBox = new CheckComboBox<>();
        checkComboBox.getItems().addAll("Option 1", "Option 2", "Option 3", "Option 4");

        StackPane root = new StackPane();
        root.getChildren().add(checkComboBox);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("CheckComboBox Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
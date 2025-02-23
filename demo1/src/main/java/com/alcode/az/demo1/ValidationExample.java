package com.alcode.az.demo1;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ValidationExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        ValidationSupport validationSupport = new ValidationSupport();

        validationSupport.registerValidator(textField, Validator.createEmptyValidator("Field is required"));

        VBox root = new VBox(10);
        root.getChildren().add(textField);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Validation Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
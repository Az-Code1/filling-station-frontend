package com.alcode.az.fillingstation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CustomToggleSwitch extends Application {

    @Override
    public void start(Stage primaryStage) {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.getStyleClass().add("toggle-button");

        // Add the thumb (circle)
        Circle thumb = new Circle(10, 10, 10); // Radius of 10
        thumb.setStyle("-fx-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0.5, 0, 1);");

        // StackPane for overlaying the thumb
        StackPane stackPane = new StackPane(toggleButton, thumb);
        stackPane.setPrefSize(50, 25);

        // Update thumb position on toggle
        toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                thumb.setTranslateX(15); // Move to right
            } else {
                thumb.setTranslateX(-15); // Move to left
            }
        });

        Scene scene = new Scene(stackPane, 300, 200);
        scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

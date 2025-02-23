package com.alcode.az.fillingstation.controller;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CustomToggleButton {
    private final StackPane stackPane;


    public CustomToggleButton() {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.getStyleClass().add("toggle-button");

        // Add the thumb (circle)
        Circle thumb = new Circle(10, 10, 10); // Radius of 10
        thumb.setStyle("-fx-fill: #3498DB; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0.5, 0, 1);");

        // StackPane for overlaying the thumb
        stackPane = new StackPane(toggleButton, thumb);
        stackPane.setPrefSize(50, 25);

        // Update thumb position on toggle
        toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                thumb.setTranslateX(15); // Move to right
                thumb.setFill(Color.LIMEGREEN);
            } else {
                thumb.setTranslateX(-15); // Move to left
                thumb.setFill(Color.ORANGERED);
            }
        });
    }

    public StackPane getToggleStackPane() {
        return this.stackPane;
    }

    public Circle getThumb() {

        return (Circle) stackPane.getChildren().get(1);
    }
}

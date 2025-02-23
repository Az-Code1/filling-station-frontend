package com.alcode.az.fillingstation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public class DigitalClock extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label timeLabel = new Label("12:45:30");

        // Load Digital-7 font from resources/fonts
        String fontPath = "fonts/Digital-7.ttf";  // Ensure this matches the actual path
        InputStream fontStream = getClass().getResourceAsStream(fontPath);
        if (fontStream != null) {
            Font digitalFont = Font.loadFont(fontStream, 40);  // Set size to 40px
            timeLabel.setFont(digitalFont);
        } else {
            System.out.println("Font file not found in resources!");
        }

        StackPane root = new StackPane(timeLabel);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Digital Clock");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
// Compare this snippet from src/main/java/com/alcode/az/fillingstation/DigitalClock.java:
package com.alcode.az.demo1;

import org.controlsfx.control.RangeSlider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RangeSliderExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        RangeSlider rangeSlider = new RangeSlider(0, 100, 20, 80);
        rangeSlider.setShowTickLabels(true);
        rangeSlider.setShowTickMarks(true);

        StackPane root = new StackPane();
        root.getChildren().add(rangeSlider);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("RangeSlider Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
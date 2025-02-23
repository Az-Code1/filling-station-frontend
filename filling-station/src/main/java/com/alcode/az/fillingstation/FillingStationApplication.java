package com.alcode.az.fillingstation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FillingStationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FillingStationApplication.class
                .getResource("branch-welcome-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        String css = Objects.requireNonNull(getClass().getResource("styles/styles.css"))
                .toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Filling Station App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
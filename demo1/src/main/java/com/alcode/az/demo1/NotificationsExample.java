package com.alcode.az.demo1;

import org.controlsfx.control.Notifications;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NotificationsExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Show Notification");
        button.setOnAction(e -> {
            Notifications.create()
                    .title("Notification Title")
                    .text("This is a sample notification!")
                    .showInformation();
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Notifications Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
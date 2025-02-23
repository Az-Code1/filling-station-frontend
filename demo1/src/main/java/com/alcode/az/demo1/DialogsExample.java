package com.alcode.az.demo1;

import org.controlsfx.dialog.LoginDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DialogsExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Show Dialog");
        button.setOnAction(e -> {
            Dialog.create()
                    .title("Dialog Title")
                    .masthead("Dialog Masthead")
                    .message("This is a sample dialog.")
                    .showInformation();
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Dialogs Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

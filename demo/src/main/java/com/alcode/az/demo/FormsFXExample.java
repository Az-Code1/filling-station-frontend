package com.alcode.az.demo;

import com.dlsc.formsfx.model.structure.*;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class FormsFXExample extends Application {

    @Override
    public void start(Stage stage) {
        // Create the model
        User user = new User();

        // Create the form
        Form form = Form.of(
                Group.of(
                        Field.ofStringType(user.nameProperty())
                                .label("Name")
                                .placeholder("Enter your name")
                                .tooltip("This is your full name"),
                        Field.ofStringType(user.nameProperty())
                                .label("Name")
                                .placeholder("Enter your name")
                                .tooltip("This is your full name"),
                        Field.ofStringType(user.emailProperty())
                                .label("Email")
                                .placeholder("Enter your email")
                                .tooltip("This is your email address")
                                .validate(StringLengthValidator.atLeast(5, "Email must be at least 5 characters"))
                )
        );

        // Set up the form renderer
        VBox root = new VBox(new FormRenderer(form));
        Scene scene = new Scene(root, 400, 300);

        // Style the form (optional)
        scene.getStylesheets().add(Objects.requireNonNull(getClass().
                getResource("styles/formsfx-styles.css")).toExternalForm());

        // Set the stage
        stage.setTitle("FormsFX Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

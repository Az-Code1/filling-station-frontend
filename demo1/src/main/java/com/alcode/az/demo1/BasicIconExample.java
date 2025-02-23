package com.alcode.az.demo1;

import org.kordamp.ikonli.javafx.FontIcon;
import org.w3c.dom.css.CSSRule;//kordamp.bootstrapfx.BootstrapFX;//ikonli.fontawesome5.FontAwesomeSolid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BasicIconExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a FontIcon using the FontAwesomeSolid icon pack
        FontIcon icon = new FontIcon(FontAwesomeSolid.HEART);
        icon.setIconSize(48); // Set the size of the icon
        icon.setIconColor(javafx.scene.paint.Color.RED); // Set the color of the icon

        StackPane root = new StackPane();
        root.getChildren().add(icon);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Basic Icon Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

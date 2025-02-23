package com.alcode.az.demo1;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BasicTileExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Tile tile = TileBuilder.create()
                .title("Basic Tile")
                .text("Hello, TilesFX!")
                .build();

        StackPane root = new StackPane();
        root.getChildren().add(tile);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Basic Tile Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

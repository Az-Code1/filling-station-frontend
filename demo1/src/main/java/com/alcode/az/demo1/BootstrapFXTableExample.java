package com.alcode.az.demo1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BootstrapFXTableExample extends Application {

    public static class Person {
        private final String name;
        private final String email;

        public Person(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> table = new TableView<>();
        table.getStyleClass().add("table");

        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(nameColumn, emailColumn);

        table.setItems(FXCollections.observableArrayList(
                new Person("John Doe", "john@example.com"),
                new Person("Jane Doe", "jane@example.com")
        ));

        VBox root = new VBox(table);
        root.getStyleClass().add("container");

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        primaryStage.setTitle("BootstrapFX Table Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

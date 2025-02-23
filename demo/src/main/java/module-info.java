module com.alcode.az.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.alcode.az.demo to javafx.fxml;
    exports com.alcode.az.demo;
}
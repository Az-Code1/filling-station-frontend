module com.alcode.az.demo {
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.controlsfx.controls;
    requires jdk.xml.dom;

    opens com.alcode.az.demo1 to javafx.fxml;
    exports com.alcode.az.demo1;
}
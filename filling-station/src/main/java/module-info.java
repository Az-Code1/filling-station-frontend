module com.alcode.az.fillingstation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.net.http;
    requires webcam.capture;
    requires opencv;
    requires spring.webflux;
    requires reactor.core;
    requires jakarta.persistence;
    requires static lombok;
    requires org.apache.commons.net;
    requires org.json;
    requires annotations;
    requires spring.web;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;

    opens com.alcode.az.fillingstation to javafx.fxml, javafx.base;
    exports com.alcode.az.fillingstation;

    exports com.alcode.az.fillingstation.controller;
    opens com.alcode.az.fillingstation.controller to javafx.fxml, javafx.base;

    exports com.alcode.az.fillingstation.model;
    opens com.alcode.az.fillingstation.model to javafx.fxml, javafx.base;

    exports com.alcode.az.fillingstation.service;
    opens com.alcode.az.fillingstation.service to javafx.fxml, javafx.base;

    exports com.alcode.az.fillingstation.viewmodel;
    opens com.alcode.az.fillingstation.viewmodel to javafx.fxml, javafx.base;

    // <-- Added this line
}

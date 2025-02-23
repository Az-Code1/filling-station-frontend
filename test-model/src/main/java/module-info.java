module com.alcode.az.testmodel {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    requires jakarta.persistence;

    opens com.alcode.az.testmodel to javafx.fxml, javafx.base;
    exports com.alcode.az.testmodel;

    exports com.alcode.az.testmodel.controller;
    opens com.alcode.az.testmodel.controller to javafx.fxml, javafx.base;

    exports com.alcode.az.testmodel.entity;
    opens com.alcode.az.testmodel.entity to javafx.fxml, javafx.base;

    exports com.alcode.az.testmodel.service;
    opens com.alcode.az.testmodel.service to javafx.fxml, javafx.base;

}
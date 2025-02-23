package com.alcode.az.fillingstation.controller;

import com.alcode.az.fillingstation.service.DigitalClock;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CentralWelcomePageController implements Initializable {

    @FXML
    public Label timeLabel;
    @FXML
    public HBox timeHBox;
    @FXML
    private Button centralSettingsButton;
    @FXML
    private Button centralDashboardButton;
    @FXML
    private ScrollPane scrollPane; // The ScrollPane in MainView.fxml

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/supervisor-view.fxml"));
            Node content = loader.load();
            scrollPane.setContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

// Add label to HBox safely
        Platform.runLater(() -> {
            timeLabel.setText("12:45:30");
        });

    }
    public void centralDashboardLoader(ActionEvent ae) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/central-dashboard-view.fxml"));
        Node content = loader.load();
        scrollPane.setContent(content);
    }

    public void centralSettingsLoader(ActionEvent ae) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/central-admin-settings-page-view.fxml"));
        Node content = loader.load();
        scrollPane.setContent(content);
    }

    public void attendanceLoader() {
        try {
            // Debug: Check if the FXML file exists
            URL fxmlUrl = getClass().getResource("/com/alcode/az/fillingstation/supervisor-view.fxml");
            if (fxmlUrl == null) {
                System.err.println("FXML file not found!");
                return;
            }

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Node content = loader.load();

            // Set the loaded FXML to the ScrollPane
            scrollPane.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML file: " + e.getMessage());
        }
    }
    public Label getTimeLabel(Label timeLabel) {
        // Load Digital-7 font from resources/fonts
        String fontPath = "/com/alcode/az/fillingstation/fonts/digital-7.ttf";  // Ensure this matches the actual path
        InputStream fontStream = DigitalClock.class.getResourceAsStream(fontPath);
        if (fontStream != null) {
            Font digitalFont = Font.loadFont(fontStream, 100);  // Set size to 40px
            timeLabel.setFont(digitalFont);
            System.out.println("Font file found in resources.");
            return timeLabel;
        } else
            System.out.println("Font file not found in resources!");
        return timeLabel;
    }

}
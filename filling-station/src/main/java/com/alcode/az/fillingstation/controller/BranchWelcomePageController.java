package com.alcode.az.fillingstation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BranchWelcomePageController implements Initializable {

    @FXML
    private Button supervisorButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button accountsButton;
    @FXML
    public Label timeLabel;
    @FXML
    private HBox timeHBox;
    @FXML
    private Button profileButton;
    @FXML
    private Button branchDashboardButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        branchDashboardButton.fire();
    }

    @FXML
    private void onBranchDashboardButton() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/branch-dashboard-view.fxml"));
            Node content = loader.load();
            scrollPane.setContent(content);
        } catch ( IOException e) {
            System.out.println("Error loading central dashboard view");
        }
    }

    @FXML
    public void onProfileButton(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/branch-profile-view.fxml"));
            Node content = loader.load();
            scrollPane.setContent(content);
        } catch ( IOException e) {
            System.out.println("Error loading central dashboard view");
        }
    }

    @FXML
    public void onStaffButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/staff-view.fxml"));
            Node content = loader.load();
            scrollPane.setContent(content);
        } catch ( IOException e) {
            e.printStackTrace();
            System.out.println("Error loading central staff view page" +  "\n" +Arrays.toString(e.getStackTrace())
                    + e.getMessage()+"\n\n" + e.getLocalizedMessage());
        }
    }

    @FXML
    public void onSupervisorButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/supervisor-view.fxml"));
            Node content = loader.load();
            scrollPane.setContent(content);
        } catch ( IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    @FXML
    public void onAccountsButton(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/alcode/az/fillingstation/branch-account-view.fxml"));
            Node content = loader.load();
            scrollPane.setContent(content);
        } catch ( IOException e) {
            System.out.println("Error loading central dashboard view");
        }
    }

    @FXML
    private void onSocialButton(ActionEvent actionEvent) {
    }
}

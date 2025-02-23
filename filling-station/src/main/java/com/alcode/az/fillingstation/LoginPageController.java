package com.alcode.az.fillingstation;

import com.alcode.az.fillingstation.model.Customer;
import com.alcode.az.fillingstation.service.CustomerServiceClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class LoginPageController extends Application implements Initializable{

    @FXML
    private HBox revealPasswordHBox;
    @FXML
    private HBox concealPasswordHBox;
    @FXML
    private Button revealPasswordButton;
    @FXML
    private Button concealPasswordButton;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private CheckBox rememberMeCheckbox;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button forgotPasswordButton;
    @FXML
    private Text responseText;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    private String password;


    @Override
    public void start(Stage stage) throws IOException {

        try {
            System.out.println(CustomerServiceClient.getAllCustomers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(FillingStationApplication.class
                .getResource("login-page-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        String css = Objects.requireNonNull(getClass().getResource("styles/styles.css"))
                .toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Filling Station App!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void handleCustomer(Customer customer) {
        System.out.println("I handled Customer received: " + customer);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonAction();
        passwordTextField.textProperty().bindBidirectional(passwordPasswordField.textProperty());
    }
    void buttonAction() {
        loginButton.setOnAction(e -> {
            //jusxuhuhi@fgbn/636gt6
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String response;
            try {
                response = sendGetRequest("http://localhost:8080/filling-station/users/details/"
                    +username+"/"+password);
            } catch (Exception ex) {
                response = "unable to connect to server";
                responseText.setText("Unable to connect to server");
            }
            if(response.equals("true")){
                try {
                    new FillingStationApplication().start(new Stage());
//                    stage.close();
                } catch (IOException ex) {

                    responseText.setText("Unable to open App");
                }
            } else {
                System.out.println("Response from server error "+response);
                responseText.setFill(Color.RED);
                responseText.setText("Username or password is incorrect");
            }

        });

        signUpButton.setOnAction(e -> {
            String jsonInputString = "   {\n" +
                    "    \"username\": \"Moyoye\",\n" +
                    "    \"email\": \"aze99@x0000fgbn\",\n" +
                    "    \"phoneNumber\": \"0815990000\",\n" +
                    "    \"password\": \"t59.,az\",\n" +
                    "    \"role\": \"marketer\",\n" +
                    "    \"ipAddress\": \"1929.827.28.22\",\n" +
                    "    \"branchId\": 2,\n" +
                    "    \"timeStamp\": \"828.27.27\"\n" +
                    "}         ";
            String response = sendPostRequest("http://localhost:8080/users", jsonInputString);
            responseText.setText("Sign up button action ended and response is " + response);
        });

    }

    private String sendPostRequest(String urlString, String jsonInputString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "POST request failed: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private String sendGetRequest(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "GET request failed: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    @FXML
    public void onLoginButton(ActionEvent actionEvent) {
    }
    @FXML
    public void onSignUpButton(ActionEvent actionEvent) {
    }
    @FXML
    public void onForgotPasswordButton(ActionEvent actionEvent) {
    }
    @FXML
    public void onConcealPasswordButton(ActionEvent actionEvent) {
        revealPasswordHBox.setVisible(true);
        concealPasswordHBox.setVisible(false);
    }
    @FXML
    public void onRevealPasswordButton(ActionEvent actionEvent) {
        revealPasswordHBox.setVisible(false);
        concealPasswordHBox.setVisible(true);
    }
}



//public class CustomerController {
//
//    @FXML
//    private TextField customerIdField;
//
//    @FXML
//    private TextField customerNameField;
//
//    @FXML
//    private TextField customerPhoneNumberField;
//
//    @FXML
//    private TextField companyNameField;
//
//    @FXML
//    private void handleModifyCustomer() {
//        Long customerId = Long.parseLong(customerIdField.getText());
//        String customerName = customerNameField.getText();
//        String customerPhoneNumber = customerPhoneNumberField.getText();
//        String companyName = companyNameField.getText();
//
//        // Create JSON payload
//        String json = String.format(
//                "{\"customerName\":\"%s\", \"customerPhoneNumber\":\"%s\", \"companyName\":\"%s\"}",
//                customerName, customerPhoneNumber, companyName
//        );
//
//        try {
//            String response = CustomerServiceClient.modifyCustomer(customerId, json);
//            System.out.println("Customer modified: " + response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


//package io.aljavap.fillingStation.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // Disable CSRF for simplicity (enable in production)
//                .authorizeHttpRequests()
//                .requestMatchers("/api/customers/**").authenticated() // Secure customer endpoints
//                .anyRequest().permitAll() // Allow other requests
//                .and()
//                .httpBasic(); // Use HTTP Basic Authentication
//        return http.build();
//    }
//}


//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//
//public class CustomerController {
//
//    @FXML
//    private TextField nameField;
//
//    @FXML
//    private TextField emailField;
//
//    @FXML
//    private void handleCreateCustomer() {
//        String name = nameField.getText();
//        String email = emailField.getText();
//
//        String json = String.format("{\"name\":\"%s\",\"email\":\"%s\"}", name, email);
//
//        try {
//            String response = ApiClient.createCustomer(json);
//            System.out.println("Customer created: " + response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
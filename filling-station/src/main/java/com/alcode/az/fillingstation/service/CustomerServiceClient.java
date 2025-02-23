package com.alcode.az.fillingstation.service;

import com.alcode.az.fillingstation.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;

public class CustomerServiceClient {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "http://localhost:8080/api/customers";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private static String getAuthHeader() {
        String auth = USERNAME + ":" + PASSWORD;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    // Get all customers
    public static String getAllCustomers() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", getAuthHeader())
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Convert JSON string to List of Customers
    public static List<Customer> jsonToCustomersList(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<>() {
        });
    }

    public static String convertArrayListToJson(String input) {
        // Remove square brackets
        input = input.replaceAll("[\\[\\]]", "");

        // Extract the object type and key-value pairs
        String objectType = input.substring(0, input.indexOf('('));
        String keyValuePairs = input.substring(input.indexOf('(') + 1, input.indexOf(')'));

        // Split key-value pairs
        String[] pairs = keyValuePairs.split(", ");

        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            jsonObject.put(key, value);
        }

        // Optionally, add the object type to the JSON
        jsonObject.put("objectType", objectType);

        return jsonObject.toString(2); // Pretty-print with an indent of 2 spaces
    }
    @NotNull
    private static JSONObject getJsonObject(String input) {
        String keyValuePairs = input.substring(input.indexOf('(') + 1, input.indexOf(')'));

        // Split key-value pairs
        String[] pairs = keyValuePairs.split(", ");

        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            jsonObject.put(key, value);
        }
        return jsonObject;
    }

    // Create a new customer
    public static String createCustomer(String json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Modify an existing customer
    public static String modifyCustomer(Long customerId, String json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + customerId)) // Append customerId to the URL
                .header("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json)) // Use PUT for modification
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
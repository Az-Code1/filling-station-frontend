package com.alcode.az.testmodel.service;

import com.alcode.az.testmodel.entity.Customer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CustomerService {
    private static final String API_URL = "http://localhost:8080/api/customers";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Customer> getAllCustomers() {
        Customer[] customers = restTemplate.getForObject(API_URL, Customer[].class);
        return Arrays.asList(customers != null ? customers : new Customer[0]);
    }

    public void addCustomer(Customer customer) {
        restTemplate.postForObject(API_URL, customer, Customer.class);
    }
}

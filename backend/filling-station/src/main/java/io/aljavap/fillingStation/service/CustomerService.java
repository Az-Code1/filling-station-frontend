package io.aljavap.fillingStation.service;

import io.aljavap.fillingStation.entity.Customer;
import io.aljavap.fillingStation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create or save a customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    // Update a customer
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setCustomerName(customerDetails.getCustomerName());
            customer.setCustomerPhoneNumber(customerDetails.getCustomerPhoneNumber());
            customer.setCompanyName(customerDetails.getCompanyName());
            return customerRepository.save(customer);
        }
        return null; // Or throw an exception
    }

    // Delete a customer
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
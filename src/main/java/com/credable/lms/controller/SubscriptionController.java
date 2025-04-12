package com.credable.lms.controller;

import com.credable.lms.model.Customer;
import com.credable.lms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam String customerNumber) {
        if (customerRepository.findByCustomerNumber(customerNumber).isPresent()) {
            return ResponseEntity.badRequest().body("Customer already subscribed.");
        }

        Customer newCustomer = new Customer(customerNumber);
        customerRepository.save(newCustomer);

        return ResponseEntity.ok("Customer subscribed successfully.");
    }
}


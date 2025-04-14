package com.credable.lms.controller;

import com.credable.lms.model.Customer;
import com.credable.lms.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Subscription Controller", description = "API for customer subscription management")
public class SubscriptionController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/subscribe")
    @Operation(
        summary = "Subscribe a customer",
        description = "Adds a new customer to the system using the provided customer number"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer subscribed successfully"),
        @ApiResponse(responseCode = "400", description = "Customer already subscribed")
    })
    public ResponseEntity<String> subscribe(
            @Parameter(description = "Customer number to subscribe", example = "318411216")
            @RequestParam String customerNumber) {
        if (customerRepository.findByCustomerNumber(customerNumber).isPresent()) {
            return ResponseEntity.badRequest().body("Customer already subscribed.");
        }

        Customer newCustomer = new Customer(customerNumber);
        customerRepository.save(newCustomer);

        return ResponseEntity.ok("Customer subscribed successfully.");
    }
}


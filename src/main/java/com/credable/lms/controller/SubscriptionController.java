package com.credable.lms.controller.v1;

import com.credable.lms.dto.CustomerDto;
import com.credable.lms.model.Customer;
import com.credable.lms.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Subscription Controller", description = "API for customer subscription management")
public class SubscriptionController {

    @Autowired
    private CustomerRepository customerRepository;
    
    @PostMapping("/subscribe")
    @Operation(
        summary = "Subscribe a customer using JSON body",
        description = "Adds a new customer to the system using the provided customer data in request body"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer subscribed successfully"),
        @ApiResponse(responseCode = "400", description = "Customer already subscribed or invalid data")
    })
    public ResponseEntity<String> subscribeWithBody(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Customer data")
            @RequestBody CustomerDto customerDto) {
        
        if (customerDto == null || customerDto.getCustomerNumber() == null || customerDto.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer number and name are required.");
        }
        
        return createSubscription(customerDto.getCustomerNumber(), customerDto.getName());
    }
    
    @PostMapping("/subscribe-params")
    @Operation(
        summary = "Subscribe a customer using query parameters",
        description = "Adds a new customer to the system using the provided customer number and name as query parameters. Used for shell scripts."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer subscribed successfully"),
        @ApiResponse(responseCode = "400", description = "Customer already subscribed or invalid data")
    })
    public ResponseEntity<String> subscribeWithParams(
            @Parameter(description = "Customer number", example = "318411216", required = true)
            @RequestParam String customerNumber,
            @Parameter(description = "Customer name", example = "John Doe", required = true)
            @RequestParam String name) {
        
        if (customerNumber == null || name == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer number and name are required.");
        }
        
        return createSubscription(customerNumber, name);
    }
    
    /**
     * Helper method to avoid code duplication
     */
    private ResponseEntity<String> createSubscription(String customerNumber, String name) {
        if (customerRepository.findByCustomerNumber(customerNumber).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer already subscribed.");
        }
        
        Customer newCustomer = new Customer(customerNumber, name, "ACTIVE");
        customerRepository.save(newCustomer);
        return ResponseEntity.ok("Customer subscribed successfully.");
    }
}
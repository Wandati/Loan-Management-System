package com.credable.lms.controller;

import com.credable.lms.dto.LoanRequestDto;
import com.credable.lms.exception.CustomerNotFoundException;
import com.credable.lms.exception.PendingLoanExistsException;
import com.credable.lms.exception.LoanRejectionException;
import com.credable.lms.model.Loan;
import com.credable.lms.repository.LoanRepository;
import com.credable.lms.service.LoanProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Loan", description = "Endpoints for loan processing and status checking")
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private LoanProcessingService loanProcessingService;
    
    @PostMapping("/loanRequest")
    @Operation(
        summary = "Request a loan with parameters", 
        description = "Initiates a loan request for a given customer and amount using query parameters. Useful for shell scripts."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loan request processed successfully"),
        @ApiResponse(responseCode = "400", description = "Customer not found or pending loan exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> requestLoanWithParams(
            @Parameter(description = "Customer number", required = true, example = "318411216") 
            @RequestParam String customerNumber,
            @Parameter(description = "Loan amount", required = true, example = "5000.00") 
            @RequestParam Double amount) {
        return processLoanRequest(customerNumber, amount);
    }
    
    @PostMapping("/loanRequest/json")
    @Operation(
        summary = "Request a loan with JSON body", 
        description = "Initiates a loan request for a given customer and amount using JSON request body"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loan request processed successfully"),
        @ApiResponse(responseCode = "400", description = "Customer not found or pending loan exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> requestLoanWithBody(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Loan request details containing customer number and amount"
            )
            @RequestBody LoanRequestDto loanRequest) {
        if (loanRequest == null || loanRequest.getCustomerNumber() == null || loanRequest.getAmount() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer number and loan amount are required.");
        }
        return processLoanRequest(loanRequest.getCustomerNumber(), loanRequest.getAmount());
    }
    
    @GetMapping("/loanStatus")
    @Operation(
        summary = "Check loan status", 
        description = "Fetches the most recent loan status of a customer"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loan status retrieved successfully")
    })
    public ResponseEntity<?> getLoanStatus(
            @Parameter(description = "Customer number to fetch loan status", required = true, example = "318411216")
            @RequestParam String customerNumber) {
        Optional<Loan> loanOpt = loanRepository.findTopByCustomerNumberOrderByCreatedAtDesc(customerNumber);
        
        if (loanOpt.isEmpty()) {
            return ResponseEntity.ok("No loan found for this customer.");
        }
        
        Loan loan = loanOpt.get();
        return ResponseEntity.ok("Loan status: " + loan.getStatus());
    }
    
    @GetMapping("/loanStatus/{customerNumber}")
    @Operation(
        summary = "Check loan status using path variable", 
        description = "Fetches the most recent loan status of a customer using path variable"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loan status retrieved successfully")
    })
    public ResponseEntity<?> getLoanStatusByPath(
            @Parameter(description = "Customer number to fetch loan status", required = true, example = "318411216")
            @PathVariable String customerNumber) {
        Optional<Loan> loanOpt = loanRepository.findTopByCustomerNumberOrderByCreatedAtDesc(customerNumber);
        
        if (loanOpt.isEmpty()) {
            return ResponseEntity.ok("No loan found for this customer.");
        }
        
        Loan loan = loanOpt.get();
        return ResponseEntity.ok("Loan status: " + loan.getStatus());
    }
    
    /**
     * Helper method to process loan requests
     */
    private ResponseEntity<String> processLoanRequest(String customerNumber, Double amount) {
        try {
            String result = loanProcessingService.processLoanRequest(customerNumber, amount);
            return ResponseEntity.ok(result);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found. Please subscribe first.");
        } catch (PendingLoanExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A pending loan already exists.");
        } catch (LoanRejectionException e) {
            return ResponseEntity.ok("Loan request submitted successfully but was REJECTED after scoring.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
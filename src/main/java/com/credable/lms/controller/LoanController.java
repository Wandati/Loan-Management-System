package com.credable.lms.controller;

import com.credable.lms.exception.CustomerNotFoundException;
import com.credable.lms.exception.PendingLoanExistsException;
import com.credable.lms.exception.LoanRejectionException;
import com.credable.lms.model.Loan;
import com.credable.lms.repository.LoanRepository;
import com.credable.lms.service.LoanProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Loan", description = "Endpoints for loan processing and status checking")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanProcessingService loanProcessingService;

    @PostMapping("/loanRequest")
    @Operation(summary = "Request a loan", description = "Initiates a loan request for a given customer and amount")
    public ResponseEntity<String> requestLoan(
            @Parameter(description = "Customer number", required = true) @RequestParam String customerNumber,
            @Parameter(description = "Loan amount", required = true) @RequestParam Double amount) {
        try {
            String result = loanProcessingService.processLoanRequest(customerNumber, amount);
            return ResponseEntity.ok(result);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.badRequest().body("Customer not found. Please subscribe first.");
        } catch (PendingLoanExistsException e) {
            return ResponseEntity.badRequest().body("A pending loan already exists.");
        } catch (LoanRejectionException e) {
            return ResponseEntity.ok("Loan request submitted successfully but was REJECTED after scoring.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/loanStatus")
    @Operation(summary = "Check loan status", description = "Fetches the most recent loan status of a customer")
    public ResponseEntity<?> getLoanStatus(
            @Parameter(description = "Customer number to fetch loan status", required = true)
            @RequestParam String customerNumber) {
        Optional<Loan> loanOpt = loanRepository.findTopByCustomerNumberOrderByCreatedAtDesc(customerNumber);
        if (loanOpt.isEmpty()) {
            return ResponseEntity.ok("No loan found for this customer.");
        }
        Loan loan = loanOpt.get();
        return ResponseEntity.ok("Loan status: " + loan.getStatus());
    }
}


package com.credable.lms.service;

import com.credable.lms.exception.CustomerNotFoundException;
import com.credable.lms.exception.LoanRejectionException;
import com.credable.lms.exception.PendingLoanExistsException;
import com.credable.lms.model.Customer;
import com.credable.lms.model.Loan;
import com.credable.lms.repository.CustomerRepository;
import com.credable.lms.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanProcessingService {
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ScoringService scoringService;
    
    @Transactional
    public String processLoanRequest(String customerNumber, Double amount) {
        // Get customer or throw exception
        Customer customer = customerRepository.findByCustomerNumber(customerNumber)
            .orElseThrow(() -> new CustomerNotFoundException());
            
        // Check for ongoing loan
        if (loanRepository.existsByCustomerNumberAndStatus(customerNumber, "PENDING") ||
            loanRepository.existsByCustomerNumberAndStatus(customerNumber, "APPROVED")) {
            throw new PendingLoanExistsException();
        }
        
        // Create and save loan
        Loan loan = new Loan(customerNumber, amount);
        loanRepository.save(loan);
        
        // Initiate scoring outside the transaction boundary
        processScoringOutsideTransaction(loan, customer);
        
        return "Loan request submitted successfully and was APPROVED.";
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processScoringOutsideTransaction(Loan loan, Customer customer) {
        String scoringToken = scoringService.initiateScoring(loan.getCustomerNumber());
        
        try {
            scoringService.queryScore(scoringToken);
            loan.setStatus("APPROVED");
            customer.setStatus("LOAN_GRANTED");
        } catch (Exception e) {
            loan.setStatus("REJECTED");
            customer.setStatus("ACTIVE");
            loanRepository.save(loan);
            customerRepository.save(customer);
            throw new LoanRejectionException();
        }
        
        loanRepository.save(loan);
        customerRepository.save(customer);
    }
}

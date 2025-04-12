package com.credable.lms.repository;

import com.credable.lms.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findTopByCustomerNumberOrderByCreatedAtDesc(String customerNumber);
    boolean existsByCustomerNumberAndStatus(String customerNumber, String status);
}


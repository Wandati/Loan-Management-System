package com.credable.lms.repository;

import com.credable.lms.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findTopByCustomerNumberOrderByCreatedAtDesc(String customerNumber);
    boolean existsByCustomerNumberAndStatus(String customerNumber, String status);
    @Query("SELECT COUNT(l) > 0 FROM Loan l WHERE l.customerNumber = :customerNumber AND l.status IN :statuses")
    boolean existsByCustomerNumberAndStatusIn(@Param("customerNumber") String customerNumber, @Param("statuses") String[] statuses);
}


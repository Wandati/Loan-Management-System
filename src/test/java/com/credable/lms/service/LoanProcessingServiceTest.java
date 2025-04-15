package com.credable.lms.service;
import com.credable.lms.dto.ScoringResponse;
import com.credable.lms.exception.CustomerNotFoundException;
import com.credable.lms.exception.LoanRejectionException;
import com.credable.lms.exception.PendingLoanExistsException;
import com.credable.lms.model.Customer;
import com.credable.lms.model.Loan;
import com.credable.lms.repository.CustomerRepository;
import com.credable.lms.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanProcessingServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private ScoringService scoringService;
    @InjectMocks
    private LoanProcessingService loanProcessingService;
    private Customer customer;
    @BeforeEach
    void setUp() {
        customer = new Customer("12345", "John Doe", "ACTIVE"); // Use constructor instead of setName
    }
    @Test
    void testCustomerNotFound() {
        when(customerRepository.findByCustomerNumber("12345")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> {
            loanProcessingService.processLoanRequest("12345", 1000.0);
        });
    }
    @Test
    void testPendingLoanExists() {
        when(customerRepository.findByCustomerNumber("12345")).thenReturn(Optional.of(customer));
        when(loanRepository.existsByCustomerNumberAndStatus("12345", "PENDING")).thenReturn(true);
        assertThrows(PendingLoanExistsException.class, () -> {
            loanProcessingService.processLoanRequest("12345", 1000.0);
        });
    }
    @Test
    void testLoanRejection() {
        when(customerRepository.findByCustomerNumber("12345")).thenReturn(Optional.of(customer));
        when(loanRepository.existsByCustomerNumberAndStatus("12345", "PENDING")).thenReturn(false);
        when(scoringService.initiateScoring("12345")).thenReturn("token");
        when(scoringService.queryScore("token")).thenThrow(new RuntimeException("Scoring failed"));
        assertThrows(LoanRejectionException.class, () -> {
            loanProcessingService.processLoanRequest("12345", 1000.0);
        });
        verify(loanRepository,times(2)).save(any(Loan.class));
    }
    @Test
    void testLoanApprovalSuccess() {
        when(customerRepository.findByCustomerNumber("12345")).thenReturn(Optional.of(customer));
        when(loanRepository.existsByCustomerNumberAndStatus("12345", "PENDING")).thenReturn(false);
        when(scoringService.initiateScoring("12345")).thenReturn("token");
        ScoringResponse response = new ScoringResponse(750); // example score
        response.setLimitAmount(5000.0);
        response.setExclusion("NONE");
        response.setExclusionReason(null);
        when(scoringService.queryScore("token")).thenReturn(response);
        String result = loanProcessingService.processLoanRequest("12345", 1000.0);
        assertEquals("Loan request submitted successfully and was APPROVED.", result);
        verify(loanRepository,times(2)).save(any(Loan.class));
        verify(customerRepository).save(customer);
    }
}

package com.credable.lms.exception;


public class LoanRejectionException extends RuntimeException {
    public LoanRejectionException() {
        super("Loan was rejected after scoring");
    }
}

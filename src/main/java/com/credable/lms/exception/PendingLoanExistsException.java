package com.credable.lms.exception;


public class PendingLoanExistsException extends RuntimeException {
    public PendingLoanExistsException() {
        super("A pending loan already exists");
    }
}

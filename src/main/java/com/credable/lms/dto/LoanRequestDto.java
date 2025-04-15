package com.credable.lms.dto;

public class LoanRequestDto {
    private String customerNumber;
    private Double amount;
    
    // Getters and setters
    public String getCustomerNumber() {
        return customerNumber;
    }
    
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
} // This closing brace was likely missing

package com.credable.lms.dto;

public class KycResponse {
    private String customerNumber;
    private String fullName;
    private String nationalId;
    private String phoneNumber;

    public KycResponse() {}

    public KycResponse(String customerNumber, String fullName, String nationalId, String phoneNumber) {
        this.customerNumber = customerNumber;
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


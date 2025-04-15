package com.credable.lms.dto;

public class CustomerDto {
    private String customerNumber;
    private String name;

    public CustomerDto() {}

    public CustomerDto(String customerNumber, String name) {
        this.customerNumber = customerNumber;
        this.name = name;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


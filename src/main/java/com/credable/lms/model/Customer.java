package com.credable.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String customerNumber;

    @Column(nullable = false)
    private String status = "ACTIVE";  // ACTIVE, LOAN_PENDING, LOAN_GRANTED, etc.

    @Column(nullable = false)
    private String name;

    public Customer() {}

    public Customer(String customerNumber) {
        this.customerNumber = customerNumber;
        this.status = "ACTIVE";
    }

    public Customer(String customerNumber, String name, String status) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.status = status;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


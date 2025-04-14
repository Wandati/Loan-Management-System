package com.credable.lms.controller;

import com.credable.lms.dto.KycResponse;
import com.credable.lms.service.KycService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "KYC", description = "Operations related to customer KYC")
public class KycController {

    @Autowired
    private KycService kycService;

    @GetMapping("/kyc")
    @Operation(summary = "Get customer KYC", description = "Fetches the KYC details of a customer using their customer number")
    public KycResponse getKyc(
            @Parameter(description = "Customer number used to fetch KYC details", required = true)
            @RequestParam String customerNumber) {
        return kycService.getCustomerKyc(customerNumber);
    }
}


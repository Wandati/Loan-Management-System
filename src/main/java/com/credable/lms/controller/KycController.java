package com.credable.lms.controller;

import com.credable.lms.dto.KycResponse;
import com.credable.lms.service.KycService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "KYC", description = "Operations related to customer KYC")
public class KycController {
    @Autowired
    private KycService kycService;
    
    @GetMapping("/kyc")
    @Operation(
        summary = "Get customer KYC", 
        description = "Fetches the KYC details of a customer using their customer number"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "KYC details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public KycResponse getKyc(
            @Parameter(description = "Customer number used to fetch KYC details", required = true, example = "318411216")
            @RequestParam String customerNumber) {
        return kycService.getCustomerKyc(customerNumber);
    }
}
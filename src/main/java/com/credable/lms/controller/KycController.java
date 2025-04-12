package com.credable.lms.controller;

import com.credable.lms.dto.KycResponse;
import com.credable.lms.service.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KycController {

    @Autowired
    private KycService kycService;

    @GetMapping("/kyc")
    public KycResponse getKyc(@RequestParam String customerNumber) {
        return kycService.getCustomerKyc(customerNumber);
    }
}


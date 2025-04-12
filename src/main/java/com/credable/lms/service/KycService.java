package com.credable.lms.service;

import com.credable.lms.dto.KycResponse;
import org.springframework.stereotype.Service;

@Service
public class KycService {

    public KycResponse getCustomerKyc(String customerNumber) {
        // Simulated response as if it came from the SOAP KYC API
        return new KycResponse(
                customerNumber,
                "John Doe",
                "12345678",
                "+254700000000"
        );
    }
}


package com.credable.lms.service;

import com.credable.lms.dto.ScoringResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ScoringService {

    private Map<String, Integer> attemptsMap = new HashMap<>();

    @Autowired
    private ClientRegistrationService clientRegistrationService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://scoringtest.credable.io/api/v1/scoring";

    public String initiateScoring(String customerNumber) {
        // Simulate returning a scoring token
        return "mock-token-" + customerNumber;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public ScoringResponse queryScore(String token) {
        int attempts = attemptsMap.getOrDefault(token, 0);

        // Simulate processing delay — fail on first attempt(s)
        if (attempts < 2) {
            attemptsMap.put(token, attempts + 1);
            throw new RuntimeException("Score not ready yet. Retrying...");
        }

        // Simulate calling our own transaction data endpoint
        // In a real scenario, the Scoring Engine would call this.
        String transactionDataUrl = "http://localhost:8080/api/v1/transaction-data?customerNumber=" + token.substring(11); // Extract customerNumber from token
        restTemplate.getForObject(transactionDataUrl, Object.class);

        // Simulate a real score on final attempt
        ScoringResponse response = new ScoringResponse();
        response.setScore(new Random().nextInt(900));
        response.setLimitAmount(30000);
        response.setExclusion("No Exclusion");
        response.setExclusionReason("None");

        return response;
    }
}

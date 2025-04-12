package com.credable.lms.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientRegistrationService {

    private String scoringEngineToken = "mocked-scoring-engine-token"; // Store the token

    public String registerClient() {
        // TODO: Replace with actual HTTP POST request to Scoring Engine
        // For now, we'll just return a mock token
        return "mocked-scoring-engine-token";
    }

    public String getScoringEngineToken() {
        return scoringEngineToken;
    }
}

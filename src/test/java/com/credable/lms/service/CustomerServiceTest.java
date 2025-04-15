package com.credable.lms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientRegistrationServiceTest {

    private ClientRegistrationService clientRegistrationService;

    @BeforeEach
    void setUp() {
        clientRegistrationService = new ClientRegistrationService();
    }

    @Test
    void testRegisterClientReturnsMockToken() {
        String token = clientRegistrationService.registerClient();
        assertEquals("mocked-scoring-engine-token", token, "Token should match the mocked value");
    }

    @Test
    void testGetScoringEngineTokenReturnsCorrectToken() {
        String token = clientRegistrationService.getScoringEngineToken();
        assertEquals("mocked-scoring-engine-token", token, "Token should match the mocked value");
    }
}


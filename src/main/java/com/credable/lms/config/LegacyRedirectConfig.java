package com.credable.lms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LegacyRedirectConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // These will redirect old API calls to the new versioned endpoints
        registry.addRedirectViewController("/api/subscribe", "/api/v1/subscribe");
        registry.addRedirectViewController("/api/subscribe-params", "/api/v1/subscribe-params");
        registry.addRedirectViewController("/api/kyc", "/api/v1/kyc");
        registry.addRedirectViewController("/api/transaction-data", "/api/v1/transaction-data");
        registry.addRedirectViewController("/api/loanRequest", "/api/v1/loanRequest");
        registry.addRedirectViewController("/api/loanStatus", "/api/v1/loanStatus");
        // Add more redirects as needed
    }
}
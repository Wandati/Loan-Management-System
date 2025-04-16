package com.credable.lms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Credable LMS API")
                        .description("Loan Management System API Documentation")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Credable LMS Team")
                                .email("support@credable.com"))
                        .license(new License()
                                .name("Credable License")));
    }
}
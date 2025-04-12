package com.credable.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.context.ConfigurableApplicationContext;
import com.credable.lms.service.ClientRegistrationService;

@SpringBootApplication
@EnableRetry
public class LmsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LmsApplication.class, args);
        ClientRegistrationService clientRegistrationService = context.getBean(ClientRegistrationService.class);
        clientRegistrationService.registerClient();
    }
}

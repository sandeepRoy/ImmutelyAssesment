package com.immutlyclient.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ImmutelyPriceUpdateConfiguration {
    @Bean
    public WebClient webClientPriceUpdate() {
        return WebClient.builder().baseUrl("http://localhost:8080/immutly/products").build();
    }
}

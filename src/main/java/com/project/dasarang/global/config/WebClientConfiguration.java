package com.project.dasarang.global.config;

import com.project.dasarang.global.infra.payment.TossProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration implements WebFluxConfigurer {

    private final AppProperties appProperties;
    private final TossProperties tossProperties;

    @Bean
    public WebClient apiClient() {
        return WebClient.create(appProperties.getApiUrl());
    }

    @Bean
    public WebClient tossClient() {
        return WebClient.builder()
                .baseUrl(tossProperties.getUrl())
                .defaultHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(tossProperties.getSecretKey().getBytes()))
                .build();
    }

}

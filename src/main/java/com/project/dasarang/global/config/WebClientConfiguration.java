package com.project.dasarang.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration implements WebFluxConfigurer {

    private final AppProperties appProperties;

    @Bean
    public WebClient apiClient() {
        return WebClient.create(appProperties.getApiUrl());
    }

}

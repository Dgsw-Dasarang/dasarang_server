package com.project.dasarang.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration implements WebFluxConfigurer {

    private final AppProperties appProperties;

    @Bean
    public WebClient apiClient() {
        return WebClient.create(appProperties.getApiUrl());
    }

}

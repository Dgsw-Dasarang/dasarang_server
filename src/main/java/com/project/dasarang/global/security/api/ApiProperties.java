package com.project.dasarang.global.security.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private String key;

}

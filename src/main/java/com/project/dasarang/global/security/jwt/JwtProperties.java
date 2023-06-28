package com.project.dasarang.global.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secretKey;
    private String header;
    private String prefix;
    private Long accessExp;
    private Long refreshExp;

}

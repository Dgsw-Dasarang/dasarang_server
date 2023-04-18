package com.project.dasarang.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
@RefreshScope
public class AppProperties {

    private String apiUrl;
    private String apiKey;
    private String type;
    private Integer pIndex;
    private Integer pSize;

}

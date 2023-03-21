package com.project.dasarang.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String apiUrl;
    private String apiKey;
    private String type;
    private Integer pIndex;
    private Integer pSize;

}

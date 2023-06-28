package com.project.dasarang.global.infra.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "payment.toss")
public class TossProperties {

    private String url;
    private String secretKey;

}

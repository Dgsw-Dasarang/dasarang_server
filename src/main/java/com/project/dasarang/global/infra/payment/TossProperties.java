package com.project.dasarang.global.infra.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "payment.toss")
@RefreshScope
public class TossProperties {

    private String url;
    private String secretKey;

}

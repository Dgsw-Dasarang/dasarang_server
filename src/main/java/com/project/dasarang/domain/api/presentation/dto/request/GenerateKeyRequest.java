package com.project.dasarang.domain.api.presentation.dto.request;

import com.project.dasarang.domain.api.domain.Key;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GenerateKeyRequest {

    private String name;
    private String number;
    private String reason;

    public Key toEntity(String key) {
        return Key.builder()
                .key(key)
                .name(this.name)
                .number(this.number)
                .reason(this.reason)
                .build();
    }

}

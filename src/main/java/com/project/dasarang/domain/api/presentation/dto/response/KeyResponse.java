package com.project.dasarang.domain.api.presentation.dto.response;

import com.project.dasarang.domain.api.domain.Key;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class KeyResponse {

    private String key;
    private String name;
    private String number;
    private String reason;

    public static KeyResponse of(Key key) {
        return KeyResponse.builder()
                .key(key.getKey())
                .name(key.getName())
                .number(key.getNumber())
                .reason(key.getReason())
                .build();
    }

}

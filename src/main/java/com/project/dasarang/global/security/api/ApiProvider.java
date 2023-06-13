package com.project.dasarang.global.security.api;

import com.project.dasarang.global.security.api.domain.Key;
import com.project.dasarang.global.security.api.domain.repository.KeyRepository;
import com.project.dasarang.global.security.jwt.exception.ApiKeyForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiProvider {

    private final ApiProperties apiProperties;
    private final KeyRepository keyRepository;

    public void checkKey(String key) {
        Key apiKey = keyRepository.findById(key)
                .orElseThrow(() -> ApiKeyForbiddenException.EXCEPTION);
    }

}

package com.project.dasarang.global.security.api;

import com.project.dasarang.domain.api.domain.Key;
import com.project.dasarang.domain.api.domain.repository.KeyRepository;
import com.project.dasarang.global.security.jwt.exception.ApiKeyForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class ApiProvider {

    private final ApiProperties apiProperties;
    private final KeyRepository keyRepository;

    public void checkKey(String key) {
        byte[] encodedKeyBytes = Base64.getDecoder().decode(key);
        String encodedKeyString = new String(encodedKeyBytes);

        Key apiKey = keyRepository.findByApiKey(encodedKeyString.substring(0, encodedKeyString.length() - 1))
                .orElseThrow(() -> ApiKeyForbiddenException.EXCEPTION);
    }

}

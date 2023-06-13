package com.project.dasarang.global.security.api;

import com.project.dasarang.domain.api.domain.Key;
import com.project.dasarang.domain.api.domain.repository.KeyRepository;
import com.project.dasarang.global.security.jwt.exception.ApiKeyForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiProvider {

    private final ApiProperties apiProperties;
    private final KeyRepository keyRepository;

    public void checkKey(String key) {
        byte[] encodedKeyBytes = Base64.getDecoder().decode(key);
        String encodedKeyString = new String(encodedKeyBytes);

        log.info(encodedKeyString);
        Key apiKey = keyRepository.findByApiKey(encodedKeyString.substring(0, encodedKeyString.length() - 1))
                .orElseThrow(() -> ApiKeyForbiddenException.EXCEPTION);
    }

}

package com.project.dasarang.global.security.api;

import com.project.dasarang.global.security.jwt.exception.ApiKeyForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiProvider {

    private final ApiProperties apiProperties;

    public void checkKey(String key) {
        if(!apiProperties.getKey().equals(key))
            throw ApiKeyForbiddenException.EXCEPTION;
    }

}

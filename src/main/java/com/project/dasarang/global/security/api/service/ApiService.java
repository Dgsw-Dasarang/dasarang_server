package com.project.dasarang.global.security.api.service;

import com.project.dasarang.global.security.api.ApiProperties;
import com.project.dasarang.global.security.api.domain.Key;
import com.project.dasarang.global.security.api.domain.repository.KeyRepository;
import com.project.dasarang.global.security.api.presentation.dto.request.GenerateKeyRequest;
import com.project.dasarang.global.security.api.presentation.dto.response.KeyListResponse;
import com.project.dasarang.global.security.api.presentation.dto.response.KeyResponse;
import com.project.dasarang.global.security.jwt.exception.ApiKeyNotfoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final KeyRepository keyRepository;
    private final ApiProperties apiProperties;

    @Transactional
    public KeyResponse generateApiKey(GenerateKeyRequest request, Long exp) {
        String apiKey = Jwts.builder()
                .setSubject(request.getName())
                .claim("reason", request.getReason())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(SignatureAlgorithm.HS256, apiProperties.getKey())
                .compact();

        Key key = request.toEntity(apiKey);
        keyRepository.save(key);

        return KeyResponse.of(key);
    }

    @Transactional(readOnly = true)
    public KeyListResponse getApiKeyList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedDateTime");

        Page<Key> keyList = keyRepository.findAll(pageable);

        return KeyListResponse.builder()
                .list(keyList.map(KeyResponse::of)
                        .stream().collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public void deleteApiKey(String name, String number) {
        Key key = keyRepository.findByNameAndNumber(name, number)
                .orElseThrow(() -> ApiKeyNotfoundException.EXCEPTION);

        keyRepository.delete(key);

    }

}

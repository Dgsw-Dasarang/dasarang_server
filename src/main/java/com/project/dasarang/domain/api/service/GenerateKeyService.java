package com.project.dasarang.domain.api.service;

import com.project.dasarang.domain.api.domain.Key;
import com.project.dasarang.domain.api.domain.repository.KeyRepository;
import com.project.dasarang.domain.api.presentation.dto.request.GenerateKeyRequest;
import com.project.dasarang.domain.api.presentation.dto.response.KeyResponse;
import com.project.dasarang.global.security.api.ApiProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class GenerateKeyService {

    private final KeyRepository keyRepository;
    private final ApiProperties apiProperties;

    @Transactional
    public KeyResponse execute(GenerateKeyRequest request, Long exp) {
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

}

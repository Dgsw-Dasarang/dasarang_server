package com.project.dasarang.domain.api.service;

import com.project.dasarang.domain.api.domain.Key;
import com.project.dasarang.domain.api.domain.repository.KeyRepository;
import com.project.dasarang.global.security.jwt.exception.ApiKeyNotfoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteKeyService {

    private final KeyRepository keyRepository;

    @Transactional
    public void execute(String name, String number) {
        Key key = keyRepository.findByNameAndNumber(name, number)
                .orElseThrow(() -> ApiKeyNotfoundException.EXCEPTION);

        keyRepository.delete(key);

    }

}

package com.project.dasarang.domain.api.service;

import com.project.dasarang.domain.api.domain.Key;
import com.project.dasarang.domain.api.domain.repository.KeyRepository;
import com.project.dasarang.domain.api.presentation.dto.response.KeyListResponse;
import com.project.dasarang.domain.api.presentation.dto.response.KeyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllKeyService {

    private final KeyRepository keyRepository;

    @Transactional(readOnly = true)
    public KeyListResponse execute(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedDateTime");

        Page<Key> keyList = keyRepository.findAll(pageable);

        return KeyListResponse.builder()
                .list(keyList.map(KeyResponse::of)
                        .stream().collect(Collectors.toList()))
                .build();
    }

}

package com.project.dasarang.domain.api.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class KeyListResponse {

    private List<KeyResponse> list;

}

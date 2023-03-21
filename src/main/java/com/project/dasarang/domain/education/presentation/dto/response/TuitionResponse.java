package com.project.dasarang.domain.education.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class TuitionResponse {

    private String title;
    private int price;

}

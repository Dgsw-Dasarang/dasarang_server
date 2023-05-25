package com.project.dasarang.domain.education.presentation.dto.response;

import com.project.dasarang.global.infra.education.domain.Tuition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class TuitionResponse {

    private String title;
    private int price;

    public static TuitionResponse of(Tuition tuition) {
        return TuitionResponse.builder()
                .title(tuition.getTitle())
                .price(tuition.getPrice())
                .build();
    }

}

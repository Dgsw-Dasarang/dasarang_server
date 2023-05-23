package com.project.dasarang.domain.payment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class CheckPaymentResponse {

    private String name;
    private boolean confirm;

}

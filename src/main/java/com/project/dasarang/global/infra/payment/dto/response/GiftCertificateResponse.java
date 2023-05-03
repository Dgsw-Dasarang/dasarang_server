package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class GiftCertificateResponse implements Serializable {

    private String approveNo;
    private String settlementStatus;

}

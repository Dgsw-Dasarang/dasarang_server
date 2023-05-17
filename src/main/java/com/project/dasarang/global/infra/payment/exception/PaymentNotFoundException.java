package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PaymentNotFoundException extends BusinessException {

    public static final PaymentNotFoundException EXCEPTION = new PaymentNotFoundException();

    private PaymentNotFoundException() {
        super(ErrorCode.PAYMENT_NOT_FOUND);
    }
}

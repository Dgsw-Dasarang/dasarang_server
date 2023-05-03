package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PaymentUnauthorizedKeyException extends BusinessException {

    public static final PaymentUnauthorizedKeyException EXCEPTION = new PaymentUnauthorizedKeyException();

    private PaymentUnauthorizedKeyException() {
        super(ErrorCode.PAYMENT_UNAUTHORIZED_KEY);
    }
}

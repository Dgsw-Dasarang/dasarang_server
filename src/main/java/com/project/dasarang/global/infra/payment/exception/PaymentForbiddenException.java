package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PaymentForbiddenException extends BusinessException {

    public static final PaymentForbiddenException EXCEPTION = new PaymentForbiddenException();

    private PaymentForbiddenException() {
        super(ErrorCode.PAYMENT_FORBIDDEN);
    }
}

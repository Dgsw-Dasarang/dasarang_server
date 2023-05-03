package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PaymentServerException extends BusinessException {

    public static final PaymentServerException EXCEPTION = new PaymentServerException();

    private PaymentServerException() {
        super(ErrorCode.PAYMENT_INTERNAL_SERVER_ERROR);
    }
}

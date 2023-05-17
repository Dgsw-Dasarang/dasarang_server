package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PaymentAlreadyExistsException extends BusinessException {

    public static final PaymentAlreadyExistsException EXCEPTION = new PaymentAlreadyExistsException();

    private PaymentAlreadyExistsException() {
        super(ErrorCode.PAYMENT_ALREAY_EXISTS);
    }
}

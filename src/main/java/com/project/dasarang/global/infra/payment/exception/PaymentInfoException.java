package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PaymentInfoException extends BusinessException {

    public static final PaymentInfoException EXCEPTION = new PaymentInfoException();

    private PaymentInfoException() {
        super(ErrorCode.PAYMENT_INFO_BADREQUEST);
    }
}

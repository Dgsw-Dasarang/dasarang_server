package com.project.dasarang.global.infra.payment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class CardAlreadyExistsException extends BusinessException {

    public static final CardAlreadyExistsException EXCEPTION = new CardAlreadyExistsException();

    private CardAlreadyExistsException() {
        super(ErrorCode.CARD_ALREADY_EXISTS);
    }
}

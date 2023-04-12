package com.project.dasarang.domain.user.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class OwnerForbiddenException extends BusinessException {

    public static final OwnerForbiddenException EXCEPTION = new OwnerForbiddenException();

    private OwnerForbiddenException() {
        super(ErrorCode.OWNER_FORBIDDEN);
    }
}

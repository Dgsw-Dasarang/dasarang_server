package com.project.dasarang.global.security.jwt.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class ApiKeyForbiddenException extends BusinessException {

    public static final ApiKeyForbiddenException EXCEPTION = new ApiKeyForbiddenException();

    private ApiKeyForbiddenException() {
        super(ErrorCode.KEY_FORBIDDEN);
    }
}

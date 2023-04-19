package com.project.dasarang.global.security.jwt.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class ApiKeyNotfoundException extends BusinessException {

    public static final ApiKeyNotfoundException EXCEPTION = new ApiKeyNotfoundException();

    private ApiKeyNotfoundException() {
        super(ErrorCode.KEY_NOT_FOUND);
    }
}

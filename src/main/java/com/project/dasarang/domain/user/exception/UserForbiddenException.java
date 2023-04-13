package com.project.dasarang.domain.user.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class UserForbiddenException extends BusinessException {

    public static final UserForbiddenException EXCEPTION = new UserForbiddenException();

    private UserForbiddenException() {
        super(ErrorCode.USER_FORBIDDEN);
    }
}

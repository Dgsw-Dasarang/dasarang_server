package com.project.dasarang.domain.user.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class UserAlreadyActiveException extends BusinessException {

    public static final UserAlreadyActiveException EXCEPTION = new UserAlreadyActiveException();
    private UserAlreadyActiveException() {
        super(ErrorCode.USER_ALREADY_ACTIVE);
    }
}

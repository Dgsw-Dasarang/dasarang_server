package com.project.dasarang.domain.user.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

package com.project.dasarang.domain.admin.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class AdminForbiddenException extends BusinessException {

    public static final AdminForbiddenException EXCEPTION = new AdminForbiddenException();

    private AdminForbiddenException() {
        super(ErrorCode.ADMIN_FORBIDDEN);
    }
}

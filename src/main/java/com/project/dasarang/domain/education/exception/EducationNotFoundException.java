package com.project.dasarang.domain.education.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class EducationNotFoundException extends BusinessException {

    public static final EducationNotFoundException EXCEPTION = new EducationNotFoundException();

    private EducationNotFoundException() {
        super(ErrorCode.EDUCATION_NOT_FOUND);
    }
}

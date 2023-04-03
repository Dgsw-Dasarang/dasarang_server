package com.project.dasarang.domain.upload.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class ImageWrongException extends BusinessException {

    public static final ImageWrongException EXCEPTION = new ImageWrongException();

    private ImageWrongException() {
        super(ErrorCode.IMAGE_WRONG);
    }
}

package com.project.dasarang.domain.upload.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class ImageMainExistsException extends BusinessException {

    public static final ImageMainExistsException EXCEPTION = new ImageMainExistsException();

    private ImageMainExistsException() {
        super(ErrorCode.IMAGE_MAIN_EXISTS);
    }
}

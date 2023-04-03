package com.project.dasarang.domain.upload.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class ImageUsedException extends BusinessException {

    public static final ImageUsedException EXCEPTION = new ImageUsedException();

    private ImageUsedException() {
        super(ErrorCode.IMAGE_USED);
    }
}

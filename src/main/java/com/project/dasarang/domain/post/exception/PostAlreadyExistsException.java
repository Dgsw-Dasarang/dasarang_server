package com.project.dasarang.domain.post.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PostAlreadyExistsException extends BusinessException {

    public static final PostAlreadyExistsException EXCEPTION = new PostAlreadyExistsException();

    private PostAlreadyExistsException() {
        super(ErrorCode.POST_CONFLICT);
    }
}

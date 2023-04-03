package com.project.dasarang.domain.post.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class PostCreateWrongException extends BusinessException {

    public static final PostCreateWrongException EXCEPTION = new PostCreateWrongException();

    private PostCreateWrongException() {
        super(ErrorCode.POST_CREATE_WRONG);
    }
}

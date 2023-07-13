package com.project.dasarang.domain.comment.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class CommentEmptyException extends BusinessException {

    public static final CommentEmptyException EXCEPTION = new CommentEmptyException();

    private CommentEmptyException() {
        super(ErrorCode.COMMENT_EMPTY);
    }
}

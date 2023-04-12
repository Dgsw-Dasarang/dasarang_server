package com.project.dasarang.domain.news.exception;

import com.project.dasarang.global.exception.BusinessException;
import com.project.dasarang.global.exception.error.ErrorCode;

public class NewsNotFoundException extends BusinessException {

    public static final NewsNotFoundException EXCEPTION = new NewsNotFoundException();

    private NewsNotFoundException() {
        super(ErrorCode.NEWS_NOT_FOUND);
    }
}

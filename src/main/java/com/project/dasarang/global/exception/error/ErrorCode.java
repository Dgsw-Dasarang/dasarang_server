package com.project.dasarang.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorProperty{

    // 기본 코드
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    // Jwt 코드
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    // Auth 코드
    USER_CONFLICT(HttpStatus.CONFLICT, "유저가 이미 존재합니다."),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    ADMIN_FORBIDDEN(HttpStatus.FORBIDDEN, "관리자 권한이 존재하지 않습니다."),
    OWNER_FORBIDDEN(HttpStatus.FORBIDDEN, "업주 권한이 존재하지 않습니다."),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "유저 권한이 존재하지 않습니다."),
    USER_ALREADY_ACTIVE(HttpStatus.BAD_REQUEST, "이미 승인된 회원입니다."),

    // Education 코드
    EDUCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "교육을 찾을 수 없습니다."),

    // Post 코드
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    POST_CONFLICT(HttpStatus.CONFLICT, "게시글이 이미 존재합니다."),

    // Image 코드
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다."),
    IMAGE_MAIN_EXISTS(HttpStatus.CONFLICT, "메인 이미지가 이미 존재합니다."),
    IMAGE_WRONG(HttpStatus.FORBIDDEN, "접근권한이 없습니다."),
    IMAGE_USED(HttpStatus.CONFLICT, "이미지가 이미 사용중입니다."),

    // News 코드
    NEWS_NOT_FOUND(HttpStatus.NOT_FOUND, "소식을 찾을 수 없습니다."),

    // Comment 코드
    COMMENT_EMPTY(HttpStatus.NOT_FOUND, "댓글이 비어있습니다."),

    // Key 코드
    KEY_NOT_FOUND(HttpStatus.NOT_FOUND, "api key를 찾을 수 없습니다."),
    KEY_FORBIDDEN(HttpStatus.FORBIDDEN, "api key가 유효하지 않습니다."),

    // Payment 코드
    PAYMENT_INFO_BADREQUEST(HttpStatus.BAD_REQUEST, "입력한 정보가 올바르지 않습니다."),
    PAYMENT_UNAUTHORIZED_KEY(HttpStatus.UNAUTHORIZED, "인증되지 않은 키입니다."),
    PAYMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "결제를 실패하였습니다."),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "결제정보를 찾을 수 없습니다."),
    PAYMENT_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "일시적인 오류입니다. 잠시 후 다시 시도해주세요."),
    PAYMENT_ALREAY_EXISTS(HttpStatus.CONFLICT, "정기결제가 이미 승인되어있습니다."),

    // Card 코드
    CARD_ALREADY_EXISTS(HttpStatus.CONFLICT, "카드가 이미 등록되어있습니다.");

    private final HttpStatus status;
    private final String message;

}

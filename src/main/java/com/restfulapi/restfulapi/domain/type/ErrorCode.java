package com.restfulapi.restfulapi.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //회원가입
    USERID_SAME(HttpStatus.UNAUTHORIZED, "동일한 아이디가 있습니다."),
    EMAIL_TIME_OVER(HttpStatus.UNAUTHORIZED, "이메일 인증 시간 초과"),
    EMAIL_NOT_SUCCESS_KEY(HttpStatus.UNAUTHORIZED, "이메일 인증이 안됩니다.")
    ;

    private final HttpStatus status;
    private final String message;
}

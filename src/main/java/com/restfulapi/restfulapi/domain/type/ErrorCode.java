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
    EMAIL_NOT_SUCCESS_KEY(HttpStatus.UNAUTHORIZED, "이메일 인증이 안됩니다."),

    //로그인
    USER_NOT_JOIN(HttpStatus.UNAUTHORIZED,"회원가입이 안된 유저입니다."),
    USER_NOT_LOGIN(HttpStatus.UNAUTHORIZED,"아이디 또는 비밀번호가 맞지 않습니다."),

    //비밀번호 찾기
    FORGET_NO_ADDRESS(HttpStatus.BAD_REQUEST, "잘못된 주소 접근입니다.");

    ;

    private final HttpStatus status;
    private final String message;
}

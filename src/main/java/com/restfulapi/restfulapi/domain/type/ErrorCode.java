package com.restfulapi.restfulapi.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USERID_SAME(HttpStatus.UNAUTHORIZED, "동일한 아이디가 있습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}

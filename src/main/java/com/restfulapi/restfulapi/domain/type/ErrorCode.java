package com.restfulapi.restfulapi.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_YET(HttpStatus.UNAUTHORIZED, "아직.")
    ;

    private final HttpStatus status;
    private final String message;
}

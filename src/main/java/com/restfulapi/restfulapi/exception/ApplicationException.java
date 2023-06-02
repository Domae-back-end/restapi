package com.restfulapi.restfulapi.exception;

import com.restfulapi.restfulapi.domain.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationException extends RuntimeException{

    private final ErrorCode errorCode;

}

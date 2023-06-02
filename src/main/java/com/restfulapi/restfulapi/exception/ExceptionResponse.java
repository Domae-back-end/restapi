package com.restfulapi.restfulapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse<T> {

    private String result_code;
    private T result;

    public static <T> ExceptionResponse<T> error(String errorcode, T errorCode){
        return new ExceptionResponse<>(errorcode,errorCode);
    }

    public static <T> ExceptionResponse<T> success(T result){
        return new ExceptionResponse<>("Success",result);
    }
}

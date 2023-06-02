package com.restfulapi.restfulapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> applicationHandler(ApplicationException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ExceptionResponse.error(e.getErrorCode().name(),e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity.status(e.getStatusCode())
                .body(ExceptionResponse.error("유효성 검사 실패",e.getMessage()));
    }

}

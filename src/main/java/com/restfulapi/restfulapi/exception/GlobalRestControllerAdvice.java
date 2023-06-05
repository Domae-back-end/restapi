package com.restfulapi.restfulapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
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

    @ExceptionHandler(NullPointerException.class)
    public void nullPointerHandler(NullPointerException e){
        log.error("-------------------------------------------------");
        log.error(e.getMessage());
        log.error(Arrays.toString(e.getStackTrace()));
        log.error("-------------------------------------------------");
    }



}

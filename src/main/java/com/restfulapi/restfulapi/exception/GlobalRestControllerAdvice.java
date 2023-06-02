package com.restfulapi.restfulapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> applicationHandler(ApplicationException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ExceptionResponse.error(e.getErrorCode().name(),e.getErrorCode().getMessage()));
    }

}

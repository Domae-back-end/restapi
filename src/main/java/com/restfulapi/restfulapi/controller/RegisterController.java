package com.restfulapi.restfulapi.controller;

import com.restfulapi.restfulapi.domain.request.JoinRequest;
import com.restfulapi.restfulapi.exception.ExceptionResponse;
import com.restfulapi.restfulapi.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

    @PostMapping("/register")
    public ExceptionResponse<String> register(
            @RequestBody JoinRequest req
            ){
        service.register(req);
        return ExceptionResponse.success("회원가입에 성공하였습니다.");
    }

}

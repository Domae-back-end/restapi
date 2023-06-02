package com.restfulapi.restfulapi.controller;

import com.restfulapi.restfulapi.domain.request.LoginRequest;
import com.restfulapi.restfulapi.exception.ExceptionResponse;
import com.restfulapi.restfulapi.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public ExceptionResponse<String> login(
            @RequestBody LoginRequest req
            ){
        loginService.login(req);
        return ExceptionResponse.success("로그인에 성공하였습니다.");
    }


}

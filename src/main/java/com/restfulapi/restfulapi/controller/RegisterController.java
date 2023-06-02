package com.restfulapi.restfulapi.controller;

import com.restfulapi.restfulapi.domain.request.EmailRequest;
import com.restfulapi.restfulapi.domain.request.EmailSuccessRequest;
import com.restfulapi.restfulapi.domain.request.JoinRequest;
import com.restfulapi.restfulapi.exception.ExceptionResponse;
import com.restfulapi.restfulapi.service.EmailService;
import com.restfulapi.restfulapi.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ExceptionResponse<String> register(
            @RequestBody JoinRequest req
    ) {
        registerService.register(req);
        return ExceptionResponse.success("회원가입에 성공하였습니다.");
    }

    @PostMapping("/email")
    public ExceptionResponse<String> email(
            @RequestBody @Valid EmailRequest req
    ) {
        emailService.sendMessage(req);
        return ExceptionResponse.success("이메일이 성공적으로 발송하였습니다. 제한시간 30분");
    }

    @PostMapping("/email-success")
    public ExceptionResponse<String> emailCheck(
            @RequestBody @Valid EmailSuccessRequest req
    ) {
        emailService.checkEmail(req);
        return ExceptionResponse.success("이메일 인증이 완료되었습니다.");
    }

}

package com.restfulapi.restfulapi.controller;

import com.restfulapi.restfulapi.domain.request.EmailRequest;
import com.restfulapi.restfulapi.domain.request.ForgetEmailRequest;
import com.restfulapi.restfulapi.domain.request.LoginRequest;
import com.restfulapi.restfulapi.exception.ExceptionResponse;
import com.restfulapi.restfulapi.service.ForgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ForgetController {

    private final ForgetService forgetService;

    @RequestMapping(value = "/api/v1/forget", method = RequestMethod.POST)
    public ExceptionResponse<String> forgetEmail(
            @RequestBody EmailRequest req
            ){
        String randomStr = forgetService.sendMail(req);
        return ExceptionResponse.success(randomStr);
    }

    @RequestMapping(value = "/api/v1/forget/{forgetCode}")
    public ExceptionResponse<String> forgetEmaillSuccess(
            @PathVariable("forgetCode") String forgetCode,
            @RequestBody ForgetEmailRequest req
    ){
        forgetService.replacepassword(forgetCode, req);
        return ExceptionResponse.success("비밀번호 수정이 완료하였습니다.");
    }


}

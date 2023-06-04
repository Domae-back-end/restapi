package com.restfulapi.restfulapi.service;

import com.restfulapi.restfulapi.domain.request.EmailRequest;
import com.restfulapi.restfulapi.domain.request.EmailSuccessRequest;
import com.restfulapi.restfulapi.domain.type.ErrorCode;
import com.restfulapi.restfulapi.exception.ApplicationException;
import com.restfulapi.restfulapi.util.EmailUtil;
import com.restfulapi.restfulapi.util.RedisUtil;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final RedisUtil redisUtil;
    private final EmailUtil emailUtil;

    public void sendMessage(EmailRequest req) {
        String successKey = emailUtil.randomSuccess();
        emailUtil.send_email(req.email(),successKey);
        //TODO:테스트 용도로 1분 해놓음.
        redisUtil.setString("register"+req.email(),successKey,1, TimeUnit.MINUTES);
    }

    public void checkEmail(EmailSuccessRequest req){
        String successKey = redisUtil.getString("register"+req.email());
        if(successKey.isBlank() || successKey.isEmpty()){
            throw new ApplicationException(ErrorCode.EMAIL_TIME_OVER);
        }
        if(!(successKey.equals(req.successKey()))){
            throw new ApplicationException(ErrorCode.EMAIL_NOT_SUCCESS_KEY);
        }
        redisUtil.setString("register"+req.email(),"",1,TimeUnit.MILLISECONDS);

    }



}

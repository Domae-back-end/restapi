package com.restfulapi.restfulapi.service;

import com.restfulapi.restfulapi.domain.request.EmailRequest;
import com.restfulapi.restfulapi.domain.request.EmailSuccessRequest;
import com.restfulapi.restfulapi.domain.type.ErrorCode;
import com.restfulapi.restfulapi.exception.ApplicationException;
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

    public void sendMessage(EmailRequest req) {
        String successKey = randomSuccess();
        send_email(req.email(),successKey);
        //TODO:테스트 용도로 1분 해놓음.
        redisUtil.setString(req.email(),successKey,1, TimeUnit.MINUTES);
    }

    public void checkEmail(EmailSuccessRequest req){
        String successKey = redisUtil.getString(req.email());
        if(successKey.isBlank() || successKey.isEmpty()){
            throw new ApplicationException(ErrorCode.EMAIL_TIME_OVER);
        }
        if(!(successKey.equals(req.successKey()))){
            throw new ApplicationException(ErrorCode.EMAIL_NOT_SUCCESS_KEY);
        }
        redisUtil.setString(req.email(),"",1,TimeUnit.MILLISECONDS);

    }

    private String randomSuccess(){
        String randomStr = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            randomStr+=random.nextInt(9);
        }
        return randomStr;
    }

    protected void send_email(String email, String random) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername("ty_ty123@naver.com");
        javaMailSender.setPassword("");

        javaMailSender.setPort(465);

        javaMailSender.setJavaMailProperties(getMailProperties());

        //

        MimeMessage m = javaMailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
        try {
            h.setFrom("ty_ty123@naver.com");
            h.setTo(email);
            h.setSubject("인증번호 발송");
            h.setText("인증번호 : " + random);
        } catch (Exception e) {
            System.out.println(e);
        }
        javaMailSender.send(m);
    }


    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        return properties;
    }

}

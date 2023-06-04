package com.restfulapi.restfulapi.util;


import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private String secret_password="";

    public String randomSuccess(){
        String randomStr = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            randomStr+=random.nextInt(9);
        }
        return randomStr;
    }

    public void send_forgetEmail(String email, String random) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername("ty_ty123@naver.com");
        javaMailSender.setPassword(secret_password);

        javaMailSender.setPort(465);

        javaMailSender.setJavaMailProperties(getMailProperties());

        //

        MimeMessage m = javaMailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
        try {
            h.setFrom("ty_ty123@naver.com");
            h.setTo(email);
            h.setSubject("회원가입 설정");
            h.setText("회원가입 설정 창 : http://localhost:8080/forget/" + random);
        } catch (Exception e) {
            System.out.println(e);
        }
        javaMailSender.send(m);
    }

    public void send_email(String email, String random) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername("ty_ty123@naver.com");
        javaMailSender.setPassword(secret_password);

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

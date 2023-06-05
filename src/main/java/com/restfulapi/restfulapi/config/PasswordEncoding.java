package com.restfulapi.restfulapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoding {

    public BCryptPasswordEncoder getEncoding(){
        return new BCryptPasswordEncoder();
    }

}

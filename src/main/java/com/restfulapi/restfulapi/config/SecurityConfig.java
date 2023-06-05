package com.restfulapi.restfulapi.config;

import com.restfulapi.restfulapi.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final SecurityFailHandler securityFailHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(csrf -> csrf
                        .disable()
                )
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "/api/v1/register",
                                "/api/v1/email",
                                "/api/v1/email-success",
                                "/api/v1/login",
                                "/api/v1/forget",
                                "/api/forget/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginProcessingUrl("/api/v1/login")
                        .usernameParameter("userid")
                        .passwordParameter("password")
                        .failureHandler(securityFailHandler)
                )
                .userDetailsService(loginService);

        ;

        return http.build();
    }

}

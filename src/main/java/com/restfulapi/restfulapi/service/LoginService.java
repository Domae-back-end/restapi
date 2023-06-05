package com.restfulapi.restfulapi.service;

import com.restfulapi.restfulapi.config.PasswordEncoding;
import com.restfulapi.restfulapi.domain.entity.User;
import com.restfulapi.restfulapi.domain.entity.UserDetail;
import com.restfulapi.restfulapi.domain.request.LoginRequest;
import com.restfulapi.restfulapi.domain.type.ErrorCode;
import com.restfulapi.restfulapi.exception.ApplicationException;
import com.restfulapi.restfulapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoding passwordEncoding;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : " + username);
        User user = repository.findByUserId(username).get();
        user.setPassword(passwordEncoding.getEncoding().encode(user.getPassword()));
        return new UserDetail(user);
    }

}

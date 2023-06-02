package com.restfulapi.restfulapi.service;

import com.restfulapi.restfulapi.domain.entity.User;
import com.restfulapi.restfulapi.domain.request.LoginRequest;
import com.restfulapi.restfulapi.domain.type.ErrorCode;
import com.restfulapi.restfulapi.exception.ApplicationException;
import com.restfulapi.restfulapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository repository;

    public void login(LoginRequest req){
        Optional<User> user = repository.findByUserId(req.userid());
        if(user.isEmpty()){
            throw new ApplicationException(ErrorCode.USER_NOT_JOIN);
        }
        if(!(user.get().getUserId().equals(req.userid()) && user.get().getPassword().equals(req.password()))){
            throw new ApplicationException(ErrorCode.USER_NOT_LOGIN);
        }
    }

}

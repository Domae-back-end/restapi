package com.restfulapi.restfulapi.service;

import com.restfulapi.restfulapi.config.PasswordEncoding;
import com.restfulapi.restfulapi.domain.entity.User;
import com.restfulapi.restfulapi.domain.request.JoinRequest;
import com.restfulapi.restfulapi.domain.type.ErrorCode;
import com.restfulapi.restfulapi.exception.ApplicationException;
import com.restfulapi.restfulapi.exception.ExceptionResponse;
import com.restfulapi.restfulapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoding passwordEncoding;
    public void register(JoinRequest req){
        if(!(userRepository.findByUserId(req.userId()).isEmpty())){
            throw new ApplicationException(ErrorCode.USERID_SAME);
        }
        userRepository.save(User.getEntity(
                req.userId(),
                passwordEncoding.getEncoding().encode(req.password()),
                req.email(),
                req.nickname()));
    }



}

package com.restfulapi.restfulapi.service;

import com.restfulapi.restfulapi.config.PasswordEncoding;
import com.restfulapi.restfulapi.domain.entity.User;
import com.restfulapi.restfulapi.domain.request.EmailRequest;
import com.restfulapi.restfulapi.domain.request.ForgetEmailRequest;
import com.restfulapi.restfulapi.domain.request.LoginRequest;
import com.restfulapi.restfulapi.domain.type.ErrorCode;
import com.restfulapi.restfulapi.exception.ApplicationException;
import com.restfulapi.restfulapi.repository.UserRepository;
import com.restfulapi.restfulapi.util.EmailUtil;
import com.restfulapi.restfulapi.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ForgetService {
    /**
     * TODO: 이메일에 메세지를 보내게 된다면, URL 이 지급이 되고
     * TODO: 그 URL 에 접근하여 아이디와 비밀번호를 바꿀수 있다.
     * <p>
     * 랜덤한 숫자문자열로 생성하여 redis 에 저장한 후 (10분 제한)  Controller 에서 검사후 아니면
     * 바로 return 을 해버린다.
     */

    private final EmailUtil emailUtil;
    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final PasswordEncoding passwordEncoding;

    public String sendMail(EmailRequest req) {
        String randomStr = randomStr();

        emailUtil.send_forgetEmail(req.email(), randomStr);
        redisUtil.setString("forget" + req.email(), randomStr, 10, TimeUnit.MINUTES);
        return randomStr;
    }

    public void replacepassword(String forgetCode, ForgetEmailRequest req) {
        String check_forgetCode = redisUtil.getString("forget" + req.email());
        if (check_forgetCode.isEmpty() || check_forgetCode.isBlank()) {
            throw new ApplicationException(ErrorCode.FORGET_NO_ADDRESS);
        }
        if (!(check_forgetCode.equals(forgetCode))) {
            throw new ApplicationException(ErrorCode.FORGET_NO_ADDRESS);
        }
        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> {
                    throw new ApplicationException(ErrorCode.USER_NOT_JOIN);
                });
        user.setPassword(passwordEncoding.getEncoding().encode(req.password()));
        userRepository.save(user);
        redisUtil.setString("forget" + req.email(), "", 1, TimeUnit.MILLISECONDS);

    }


    private String randomStr() {
        Random random = new Random();
        StringBuffer randomBuf = new StringBuffer();
        for (int i = 0; i < 30; i++) {
            // Random.nextBoolean() : 랜덤으로 true, false 리턴 (true : 랜덤 소문자 영어, false : 랜덤 숫자)
            if (random.nextBoolean()) {
                // 26 : a-z 알파벳 개수
                // 97 : letter 'a' 아스키코드
                // (int)(random.nextInt(26)) + 97 : 랜덤 소문자 아스키코드
                randomBuf.append((char) ((int) (random.nextInt(26)) + 97));
            } else {
                randomBuf.append(random.nextInt(10));
            }
        }
        return randomBuf.toString();
    }

}

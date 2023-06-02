package com.restfulapi.restfulapi.domain.request;

import jakarta.validation.constraints.Email;

public record EmailSuccessRequest(
        @Email(message = "이메일 형식에 맞지 않습니다.")
        String email,

        String successKey
) {
}

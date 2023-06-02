package com.restfulapi.restfulapi.domain.request;

import jakarta.validation.constraints.Email;

public record EmailRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        String email
) {
}

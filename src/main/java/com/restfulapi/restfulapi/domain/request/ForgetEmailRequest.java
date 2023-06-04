package com.restfulapi.restfulapi.domain.request;

public record ForgetEmailRequest(
        String email,
        String password
) {
}

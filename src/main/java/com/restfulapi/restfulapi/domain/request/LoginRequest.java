package com.restfulapi.restfulapi.domain.request;

public record LoginRequest(
        String userid,
        String password
) {
}

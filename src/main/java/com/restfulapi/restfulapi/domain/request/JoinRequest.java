package com.restfulapi.restfulapi.domain.request;

import jakarta.persistence.Column;
import lombok.Setter;

public record JoinRequest(
        String userId,
        String password,
        String email,
        String nickname
) {
}

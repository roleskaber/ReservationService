package com.demo.demo.security.dto;

public record JwtAuthDto (
        String token,
        String refreshToken
) {
}

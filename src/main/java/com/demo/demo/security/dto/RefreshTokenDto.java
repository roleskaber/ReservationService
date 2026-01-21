package com.demo.demo.security.dto;

public record RefreshTokenDto (
        String token,
        String refreshToken
) { }

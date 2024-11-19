package com.acledabank.springjwtauth.feature.auth.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}

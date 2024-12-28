package com.acledabank.springjwtauth.domain.primary.model.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}

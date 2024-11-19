package com.acledabank.springjwtauth.feature.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}

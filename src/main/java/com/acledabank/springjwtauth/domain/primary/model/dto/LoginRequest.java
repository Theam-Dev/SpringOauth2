package com.acledabank.springjwtauth.domain.primary.model.dto;

public record LoginRequest(
        String username,
        String password
) {
}

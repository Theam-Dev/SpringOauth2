package com.acledabank.springjwtauth.domain.primary.service;

import com.acledabank.springjwtauth.domain.primary.model.dto.AuthResponse;
import com.acledabank.springjwtauth.domain.primary.model.dto.RefreshToken;

public interface AuthService {
    AuthResponse login(String username, String password);
    AuthResponse refreshToken(RefreshToken refresh);
}

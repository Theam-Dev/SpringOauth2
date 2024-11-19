package com.acledabank.springjwtauth.feature.auth;

import com.acledabank.springjwtauth.feature.auth.dto.AuthResponse;
import com.acledabank.springjwtauth.feature.auth.dto.RefreshToken;

public interface AuthService {
    AuthResponse login(String username, String password);
    AuthResponse refreshToken(RefreshToken refresh);
}

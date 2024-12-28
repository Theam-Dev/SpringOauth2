package com.acledabank.springjwtauth.domain.primary.controller;

import com.acledabank.springjwtauth.domain.primary.model.dto.AuthResponse;
import com.acledabank.springjwtauth.domain.primary.model.dto.LoginRequest;
import com.acledabank.springjwtauth.domain.primary.model.dto.RefreshToken;
import com.acledabank.springjwtauth.domain.primary.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    AuthResponse login(@RequestBody LoginRequest loginRequest) {
       return authService.login(loginRequest.username(), loginRequest.password());
    }
    @PostMapping("/refresh-token")
    AuthResponse refreshToken(@RequestBody RefreshToken refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}

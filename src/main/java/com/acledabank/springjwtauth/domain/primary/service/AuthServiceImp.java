package com.acledabank.springjwtauth.domain.primary.service;

import com.acledabank.springjwtauth.domain.primary.model.dto.AuthResponse;
import com.acledabank.springjwtauth.domain.primary.model.dto.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {
    private final DaoAuthenticationProvider daoAuth;
    private final JwtAuthenticationProvider jwtAuth;
    private final JwtEncoder accessTokenJwtEncoder;
    private final JwtEncoder refreshTokenJwtEncoder;
    @Override
    public AuthResponse login(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        auth= daoAuth.authenticate(auth);
        Instant now = Instant.now();
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet accessTokenClaimSet = JwtClaimsSet
                .builder()
                .id(auth.getName())
                .subject("Access APIs")
                .issuedAt(now)
                .claim("scope", scope)
                .expiresAt(now.plus(1, ChronoUnit.MINUTES))
                .build();
        JwtClaimsSet refreshTokenClaimsSet = JwtClaimsSet
                .builder()
                .id(auth.getName())
                .subject("Refresh APIs")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(accessTokenClaimSet);

        String token = accessTokenJwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        String refresh = refreshTokenJwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaimsSet)).getTokenValue();

        return new AuthResponse(token,refresh);
    }

    @Override
    public AuthResponse refreshToken(RefreshToken refresh) {
        Authentication auth = new BearerTokenAuthenticationToken(refresh.refreshToken());
        auth = jwtAuth.authenticate(auth);
        Instant now = Instant.now();
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet accessTokenClaimSet = JwtClaimsSet
                .builder()
                .id(auth.getName())
                .subject("Access APIs")
                .issuedAt(now)
                .claim("scope", scope)
                .expiresAt(now.plus(1, ChronoUnit.MINUTES))
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(accessTokenClaimSet);
        String token = accessTokenJwtEncoder.encode(jwtEncoderParameters).getTokenValue();

        return new AuthResponse(token, refresh.refreshToken());
    }
}

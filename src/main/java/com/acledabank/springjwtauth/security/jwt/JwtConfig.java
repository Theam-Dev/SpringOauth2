package com.acledabank.springjwtauth.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

@Configuration
public class JwtConfig {
    @Bean
    KeyPair accessTokenKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    RSAKey accessTokenRSAKey(KeyPair accessTokenKeyPair) {
        return new RSAKey
                .Builder((RSAPublicKey) accessTokenKeyPair.getPublic())
                .privateKey((RSAPrivateKey) accessTokenKeyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    JwtDecoder accessTokenJwtDecoder(RSAKey accessTokenRSAKey) {
        try {
            return NimbusJwtDecoder.withPublicKey(accessTokenRSAKey.toRSAPublicKey()).build();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    JWKSource<SecurityContext> accessTokenJwkSource(RSAKey accessTokenRSAKey) {
        JWKSet jwkSet = new JWKSet(accessTokenRSAKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    JwtEncoder accessTokenJwtEncoder(JWKSource<SecurityContext> accessTokenJwkSource) {
        return new NimbusJwtEncoder(accessTokenJwkSource);
    }

    //    RefreshToken
    @Bean
    KeyPair refreshTokenKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    RSAKey refreshTokenRSAKey(KeyPair refreshTokenKeyPair) {
        return new RSAKey
                .Builder((RSAPublicKey) refreshTokenKeyPair.getPublic())
                .privateKey((RSAPrivateKey) refreshTokenKeyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    JwtDecoder refreshTokenJwtDecoder(RSAKey refreshTokenRSAKey) {
        try {
            return NimbusJwtDecoder.withPublicKey(refreshTokenRSAKey.toRSAPublicKey()).build();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    JWKSource<SecurityContext> refreshTokenJwkSource(RSAKey refreshTokenRSAKey) {
        JWKSet jwkSet = new JWKSet(refreshTokenRSAKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    JwtEncoder refreshTokenJwtEncoder(JWKSource<SecurityContext> refreshTokenJwkSource) {
        return new NimbusJwtEncoder(refreshTokenJwkSource);
    }
}

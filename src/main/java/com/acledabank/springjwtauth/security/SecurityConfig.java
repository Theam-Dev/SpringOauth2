package com.acledabank.springjwtauth.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    DaoAuthenticationProvider configDaoAuth() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    };
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            String id = jwt.getId();
            CustomerUserDetail userDetails = (CustomerUserDetail) userDetailsService.loadUserByUsername(id);
            return userDetails.getAuthorities() .stream()
                    .map(grantedAuthority -> new SimpleGrantedAuthority(grantedAuthority.getAuthority())) .collect(Collectors.toList());
        };
        var jwtAuthenticationConverter = new JwtAuthenticationConverter(); jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }


    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider(JwtDecoder refreshTokenJwtDecoder) {
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(refreshTokenJwtDecoder);
        provider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
        return provider;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,JwtDecoder accessTokenJwtDecoder) throws Exception {
        http

                .authorizeHttpRequests(request-> request
                        .requestMatchers("/anonymous","/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority("SCOPE_report:write", "SCOPE_report:read", "SCOPE_user:read", "SCOPE_user:write")
                        .requestMatchers("/api/v1/manager/**").hasAnyAuthority("SCOPE_report:write", "SCOPE_report:read", "SCOPE_user:read")
                        .requestMatchers("/api/v1/staff/**").hasAnyAuthority("SCOPE_report:write", "SCOPE_report:read")
                        .anyRequest().authenticated()
                );
                http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(accessTokenJwtDecoder)));
                http.csrf(AbstractHttpConfigurer::disable);
                http.sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}

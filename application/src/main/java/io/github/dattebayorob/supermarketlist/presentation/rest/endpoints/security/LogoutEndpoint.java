package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.security;

import io.github.dattebayorob.supermarketlist.config.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutEndpoint {
    private final JwtTokenService jwtTokenService;

    public ResponseEntity<Void> logout(String authorization) {
        jwtTokenService.blackListToken(authorization.substring(7));
        return ResponseEntity.ok().build();
    }
}

package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.security;

import io.github.dattebayorob.supermarketlist.config.security.jwt.Jwt;
import io.github.dattebayorob.supermarketlist.config.security.jwt.JwtTokenService;
import io.github.dattebayorob.supermarketlist.config.security.jwt.JwtUserDetailsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenEndpoint {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenService jwtTokenService;

    public ResponseEntity<JwtResponse> refreshToken(String refreshToken) {
        String username = jwtTokenService.getUsernameFromToken(refreshToken);
        if ( !jwtTokenService.validateToken(refreshToken, username) ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        jwtTokenService.blackListToken(refreshToken);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        Jwt token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse().token(token.getToken()).refreshToken(token.getRefreshToken()).expiration(token.getExpiration()));
    }
}

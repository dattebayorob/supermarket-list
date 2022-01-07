package io.github.dattebayorob.supermarketlist.presentation.rest.endpoints.security;

import io.github.dattebayorob.supermarketlist.config.security.jwt.JwtTokenService;
import io.github.dattebayorob.supermarketlist.config.security.jwt.JwtUserDetailsService;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.JwtRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginEndpoint {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final JwtUserDetailsService jwtUserDetailsService;

    public ResponseEntity<JwtResponse> login(JwtRequest jwtRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());
        authenticationManager.authenticate(authentication);
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final var token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse().token(token.getToken()).refreshToken(token.getRefreshToken()).expiration(token.getExpiration()));
    }

}

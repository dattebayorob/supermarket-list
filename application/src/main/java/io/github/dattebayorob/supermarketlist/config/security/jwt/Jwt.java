package io.github.dattebayorob.supermarketlist.config.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Jwt {
    private final String token;
    private final String refreshToken;
    private final int expiration;
}

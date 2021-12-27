package io.github.dattebayorob.supermarketlist.infrastructure.security;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder(access = PRIVATE)
public final class SecuritySession {
    private String username;
    private List<String> authorities;
    private List<String> roles;

    public static SecuritySession get() {
        return SecuritySession.builder()
            .username("dattebayoRob")
            .authorities(Collections.emptyList())
            .roles(Collections.emptyList())
        .build();
    }
}

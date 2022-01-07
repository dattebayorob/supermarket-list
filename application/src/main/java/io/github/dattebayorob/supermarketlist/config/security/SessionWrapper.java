package io.github.dattebayorob.supermarketlist.config.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.UUID;

public class SessionWrapper {
    private SessionWrapper() {}

    public static Session getSession() {
        UserdetailsImpl userdetails = (UserdetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Session(userdetails.getId(), userdetails.getUsername(), userdetails.getAuthoritiesString());
    }

    @Getter
    @RequiredArgsConstructor
    public static final class Session {
        private final UUID id;
        private final String email;
        private final Set<String> authorities;
    }
}

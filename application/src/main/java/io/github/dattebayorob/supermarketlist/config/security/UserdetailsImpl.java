package io.github.dattebayorob.supermarketlist.config.security;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class UserdetailsImpl implements UserDetails {
    private final UUID id;
    private final String name;
    private final String username;
    private final String password;
    private final Set<String> authorities;
    public UserdetailsImpl(UUID id) {
        this(id, null, null, null, null);
    }

    protected Set<String> getAuthoritiesString() {
        return authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return CollectionUtil.map(authorities, SimpleGrantedAuthority::new);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
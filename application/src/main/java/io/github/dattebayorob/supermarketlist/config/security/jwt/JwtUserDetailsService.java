package io.github.dattebayorob.supermarketlist.config.security.jwt;

import io.github.dattebayorob.supermarketlist.config.security.UserdetailsImpl;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.UserJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByEmailAndEnabledTrue(username)
                .map(toUserDetails())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
    private Function<UserJpa, UserDetails> toUserDetails() {
        return userJpa -> new UserdetailsImpl(userJpa.getId(), userJpa.getName(), userJpa.getEmail(), userJpa.getPassword(), Set.of("USER"));
    }
}

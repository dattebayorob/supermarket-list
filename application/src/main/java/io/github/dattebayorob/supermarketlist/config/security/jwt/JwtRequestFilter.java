package io.github.dattebayorob.supermarketlist.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        getBearerToken(requestTokenHeader).flatMap(getUserDetails()).ifPresent( user -> {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
            );
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            getContext().setAuthentication(usernamePasswordAuthenticationToken);
        });

        filterChain.doFilter(request, response);
    }

    private Function<String, Optional<UserDetails>> getUserDetails() {
        return token -> ofNullable(jwtTokenService.getUsernameFromToken(token))
                .filter(_ignored -> getContext().getAuthentication() == null)
                .map(jwtUserDetailsService::loadUserByUsername)
                .filter(userDetails -> jwtTokenService.validateToken(token, userDetails.getUsername()));
    }

    private Optional<String> getBearerToken(String requestTokenHeader) {
        return ofNullable(requestTokenHeader)
                .filter(header -> header.startsWith("Bearer "))
                .map(header -> header.substring(7));
    }
}

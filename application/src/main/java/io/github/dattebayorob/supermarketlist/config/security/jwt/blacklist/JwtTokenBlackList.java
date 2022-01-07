package io.github.dattebayorob.supermarketlist.config.security.jwt.blacklist;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenBlackList {
    private final JwtTokenBlackListRepository jwtTokenBlackListRepository;
    public void clearExpiredTokens() {
        jwtTokenBlackListRepository.getAll().stream().filter( token ->
            Jwts.parser().parseClaimsJws(token).getBody().getExpiration().before(new Date())
        ).forEach(jwtTokenBlackListRepository::remove);
    }
    public void blackList(String token) {
        jwtTokenBlackListRepository.put(token);
    }
    public boolean isBlackListed(String token) {
        return jwtTokenBlackListRepository.exists(token);
    }
}

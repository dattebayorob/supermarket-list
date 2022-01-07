package io.github.dattebayorob.supermarketlist.config.security.jwt.blacklist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class InMemoryJwtTokenBlackListRepository implements JwtTokenBlackListRepository {
    private final Map<String, String> tokens = new ConcurrentHashMap<>();
    @Override
    public boolean exists(String token) {
        return tokens.containsKey(token);
    }

    @Override
    public void put(String token) {
        tokens.put(token, token);
    }

    @Override
    public void remove(String token) {
        if(exists(token)) {
            tokens.remove(token);
        }
    }

    @Override
    public Set<String> getAll() {
        return tokens.keySet();
    }
}

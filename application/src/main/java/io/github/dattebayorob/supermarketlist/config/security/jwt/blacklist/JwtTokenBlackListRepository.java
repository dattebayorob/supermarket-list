package io.github.dattebayorob.supermarketlist.config.security.jwt.blacklist;

import java.util.Set;

public interface JwtTokenBlackListRepository {
    boolean exists(String token);
    void put(String token);
    void remove(String token);
    Set<String> getAll();
}

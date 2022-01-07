package io.github.dattebayorob.supermarketlist.config.security.jwt;

import io.github.dattebayorob.supermarketlist.config.security.UserdetailsImpl;
import io.github.dattebayorob.supermarketlist.config.security.jwt.blacklist.JwtTokenBlackList;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {
    public static final int JWT_TOKEN_VALIDITY = 5 * 60;
    public static final int JWT_REFRESH_TOKEN_VALIDITY = 5 * 60 * 60;
    private final String secret;
    private final JwtTokenBlackList jwtTokenBlackList;
    public JwtTokenService(@Value("${jwt.secret}")String secret, JwtTokenBlackList jwtTokenBlackList) {
        this.secret = secret;
        this.jwtTokenBlackList = jwtTokenBlackList;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);

    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Jwt generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", ((UserdetailsImpl)userDetails).getId());
        claims.put("name", ((UserdetailsImpl)userDetails).getName());
        claims.put("email", userDetails.getUsername());
        claims.put("authorities", userDetails.getAuthorities());
        String token = doGenerateToken(claims, userDetails.getUsername(), JWT_TOKEN_VALIDITY);
        Map<String, Object> refreshTokenClaims = new HashMap<>();
        refreshTokenClaims.put("id", ((UserdetailsImpl) userDetails).getId());
        String refreshToken = doGenerateToken(refreshTokenClaims, userDetails.getUsername(), JWT_REFRESH_TOKEN_VALIDITY);
        return new Jwt(token, refreshToken, JWT_TOKEN_VALIDITY);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, int expiration) {
        return Jwts.builder().setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
    }

    public Boolean validateToken(String token, String username) {
        return (getUsernameFromToken(token).equals(username) && !isTokenExpired(token)) && !jwtTokenBlackList.isBlackListed(token);
    }

    public void blackListToken(String token) {
        jwtTokenBlackList.blackList(token);
    }
}

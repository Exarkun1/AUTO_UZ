package com.energizer.auto_uz.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.energizer.auto_uz.models.users.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {
    public String generateToken(Person person) {
        List<String> claims = List.of(person.getRole().name());
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + lifeTime.toMillis());

        return JWT.create()
                .withClaim("role", claims)
                .withSubject(person.getEmail())
                .withIssuedAt(issueDate)
                .withExpiresAt(expiredDate)
                .sign(Algorithm.HMAC256(secret));
    }
    public String getEmail(String token) {
        return getAllClaims(token).getSubject();
    }
    public String getRole(String token) {
        return getAllClaims(token).getClaim("role").asList(String.class).getFirst();
    }
    private DecodedJWT getAllClaims(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build().verify(token);
    }
    @Autowired
    public JwtTokenUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.life-time}") Duration lifeTime) {
        this.secret = secret;
        this.lifeTime = lifeTime;
    }
    private final String secret;
    private final Duration lifeTime;
}

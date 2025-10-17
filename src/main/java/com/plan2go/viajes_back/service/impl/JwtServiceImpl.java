package com.plan2go.viajes_back.service.impl;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.plan2go.viajes_back.service.JwtService;
import com.plan2go.viajes_back.util.JwtResult;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtServiceImpl implements JwtService {

    private String secret;
    private long ttlMinutes;

    @Autowired
    public void setSecret(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    @Autowired
    public void setTtlMinutes(@Value("${jwt.ttlMinutes}") long ttlMinutes) {
        this.ttlMinutes = ttlMinutes;
    }

    // campos calculados a partir de la config:
    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    void init() {
        algorithm = Algorithm.HMAC256(secret);
        verifier  = JWT.require(algorithm).build();
    }

    @Override
    public String generateToken(Long userId, String email, java.time.Duration ttl) {
        Instant now = Instant.now();
        Instant exp = (ttl != null) ? now.plus(ttl) : now.plus(Duration.ofMinutes(ttlMinutes));
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withClaim("email", email)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);
    }

    @Override
    public JwtResult validateToken(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            String sub = jwt.getSubject();
            String email = jwt.getClaim("email").asString();
            Instant exp = jwt.getExpiresAtAsInstant();
            Long userId = Long.valueOf(sub);
            return JwtResult.builder()
                    .valid(true)
                    .userId(userId)
                    .email(email)
                    .expiresAt(exp)
                    .build();
        } catch (JWTVerificationException e) {
            return JwtResult.builder().valid(false).build();
        }
    }
}

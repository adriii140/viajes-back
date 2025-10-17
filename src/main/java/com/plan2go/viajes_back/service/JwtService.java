package com.plan2go.viajes_back.service;

import com.plan2go.viajes_back.util.JwtResult;

public interface JwtService {

    String generateToken(Long userId, String email, java.time.Duration ttl);
    
    JwtResult validateToken(String token);
}

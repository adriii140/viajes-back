package com.plan2go.viajes_back.util;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResult {
    private boolean valid;
    private Long userId;
    private String email;
    private Instant expiresAt;
}

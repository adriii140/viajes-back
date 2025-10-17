package com.plan2go.viajes_back.api.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plan2go.viajes_back.api.dtos.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType = "Bearer";
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("user")
    private User user;
}

package com.plan2go.viajes_back.api.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String passwordHash;
    
}

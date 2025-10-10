package com.plan2go.viajes_back.api.register;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserRegister {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String passwordHash;
}

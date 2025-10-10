package com.plan2go.viajes_back.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class User {

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("correo_electronico")
    private String email;

    @JsonProperty("contrasena")
    private String passwordHash;


    
}

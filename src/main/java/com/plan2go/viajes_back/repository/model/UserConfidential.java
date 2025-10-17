package com.plan2go.viajes_back.repository.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserConfidential {

    private Long id;

    private String name;

    private String email;

    private String passwordHash;
}

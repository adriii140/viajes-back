package com.plan2go.viajes_back.repository;

import java.util.List;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.repository.model.UserConfidential;


public interface UserRepository {

    public List<User> findAllUsers();

    public boolean createUser(UserRegister userRegister);

    public UserConfidential findByEmailWithPassword(String email);

    public List<User> findByEmail(String email);
    
}

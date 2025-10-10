package com.plan2go.viajes_back.repository;

import java.util.List;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;


public interface UserRepository {

    public List<User> findAllUsers();

    public boolean createUser(UserRegister userRegister);


    
}

package com.plan2go.viajes_back.service;

import java.util.List;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;


public interface UserService {


    List<User> findAllUsers();
    boolean createUser(UserRegister user);
}

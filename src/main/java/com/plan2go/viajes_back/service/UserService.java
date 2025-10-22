package com.plan2go.viajes_back.service;

import java.util.List;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.login.LoginResponse;
import com.plan2go.viajes_back.api.login.UserLogin;
import com.plan2go.viajes_back.api.register.UserRegister;


public interface UserService {


    List<User> findAllUsers();
    boolean registerUser(UserRegister user);
    LoginResponse login (UserLogin userLogin);
    List<User> findUserByEmail(String email);
}

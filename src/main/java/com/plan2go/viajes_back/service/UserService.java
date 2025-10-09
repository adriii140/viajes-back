package com.plan2go.viajes_back.service;

import java.util.List;

import com.plan2go.viajes_back.api.dtos.User;


public interface UserService {


    List<User> findAllUsers();
    
}

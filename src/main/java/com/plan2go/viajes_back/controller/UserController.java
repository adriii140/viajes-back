package com.plan2go.viajes_back.controller;


import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  

    private UserService userService;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

  

    @GetMapping("/")
    public ResponseEntity<List<User>> list(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatusCode.valueOf(200));
    }
}

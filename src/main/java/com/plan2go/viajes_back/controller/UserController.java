package com.plan2go.viajes_back.controller;

import com.plan2go.viajes_back.entity.User;
import com.plan2go.viajes_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository users;

    @GetMapping("/api/users")
    public List<User> list(){
        return users.findAll();
    }
}

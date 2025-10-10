package com.plan2go.viajes_back.controller;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Obtiene una lista de usuarios")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @GetMapping("/")
    public ResponseEntity<List<User>> list() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Obtiene una lista de usuarios")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @PostMapping("/")
    public boolean registerUser(@RequestBody UserRegister userRegister) {
        boolean creado = userService.createUser(userRegister);
        return creado;
    }
}

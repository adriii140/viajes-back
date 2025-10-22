package com.plan2go.viajes_back.controller;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.login.LoginResponse;
import com.plan2go.viajes_back.api.login.UserLogin;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.mapstruct.control.MappingControl.Use;
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

    @Operation(summary = "Registra un nuevo usuario")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @PostMapping("/")
    public boolean registerUser(@RequestBody UserRegister userRegister) {
        return userService.registerUser(userRegister);
    }

    @Operation(summary = "Login sesión")
    @ApiResponse(responseCode = "200", description = "Login correcto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLogin userLogin) {
        LoginResponse response = userService.login(userLogin);
        if (response == null) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Obtiene un usuario por su correo electrónico")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "500", description = "No encontrado")
    @GetMapping("/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable String email) {
        List<User> users = userService.findUserByEmail(email);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(users, HttpStatusCode.valueOf(200));
    }
}

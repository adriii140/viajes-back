package com.plan2go.viajes_back.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.service.UserService;

class UserControllertest {

    UserController userController;

    @Mock
    UserService userServiceMock;


    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
        userController = new UserController();
        userController.setUserService(userServiceMock);
    }

    @Test
    void findAllUsersTest(){
        ResponseEntity<List<User>> respuesta = userController.list();
        assertNotNull(respuesta, "No se ha obtenido respuesta");
    }



    @Test
    void createUserTest(){


        boolean creado = userController.registerUser(creaRegister());

        assertTrue(creado, "No se ha creado el usuario");
    }


    private UserRegister creaRegister(){
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("test@example.com");
        userRegister.setName("test");
        userRegister.setPasswordHash("test");
        return userRegister;
    }
    

    
}

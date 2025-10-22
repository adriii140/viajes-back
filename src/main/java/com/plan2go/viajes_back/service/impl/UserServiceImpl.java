package com.plan2go.viajes_back.service.impl;

import java.util.List;

import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.mappers.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.login.LoginResponse;
import com.plan2go.viajes_back.api.login.UserLogin;
import com.plan2go.viajes_back.repository.UserRepository;
import com.plan2go.viajes_back.repository.model.UserConfidential;
import com.plan2go.viajes_back.service.JwtService;
import com.plan2go.viajes_back.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private JwtService jwtService;
    private UserMapper userMapper;

    @Value("${jwt.ttlMinutes}")
    private long ttlMinutes;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public boolean registerUser(UserRegister userRegister) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userRegister.getPasswordHash());
        userRegister.setPasswordHash(hashedPassword);
        return userRepository.createUser(userRegister);
    }

    @Override
    public LoginResponse login(UserLogin userLogin) {
        UserConfidential conf = userRepository.findByEmailWithPassword(userLogin.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (conf == null || !passwordEncoder.matches(userLogin.getPasswordHash(), conf.getPasswordHash())) {
            return null;
        }

        String token = jwtService.generateToken(conf.getId(), conf.getEmail(), null);
        Long expiresIn = ttlMinutes * 60L;
        User userDto = userMapper.toUser(conf);
        LoginResponse res = new LoginResponse();
        res.setAccessToken(token);
        res.setTokenType("Bearer");
        res.setExpiresIn(expiresIn);
        res.setUser(userDto);
        return res;

    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}

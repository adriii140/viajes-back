package com.plan2go.viajes_back.mappers;

import java.util.List;


import org.mapstruct.Mapper;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.entity.UserEntity;
import com.plan2go.viajes_back.repository.model.UserConfidential;

@Mapper(componentModel = "spring")
public interface UserMapper {


    List<User> toUserList(List<UserEntity> entity);


    User toUser(UserEntity entity);


    UserEntity toUserEntity(UserRegister userRegister);


    UserConfidential toUserConfidential(UserEntity userEntity);

    User toUser(UserConfidential userConfidential);



    
} 

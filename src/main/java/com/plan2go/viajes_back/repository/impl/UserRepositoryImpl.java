package com.plan2go.viajes_back.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.entity.UserEntity;
import com.plan2go.viajes_back.mappers.UserMapper;
import com.plan2go.viajes_back.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAllUsers() {
        String sqlConsulta = "SELECT u FROM UserEntity u";
        Query query = (Query) entityManager.createQuery(sqlConsulta, UserEntity.class);
        List<UserEntity> userEntities = query.getResultList();


        return userMapper.toUserList(userEntities);
    }

    @Override
    @Transactional
    public boolean createUser(UserRegister userRegister) {
        UserEntity userEntity = userMapper.toUserEntity(userRegister);
        if(userEntity== null){
            return false;
        }
        entityManager.persist(userEntity);
        return true;
    }
    
}

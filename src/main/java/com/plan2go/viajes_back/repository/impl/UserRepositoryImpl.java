package com.plan2go.viajes_back.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.plan2go.viajes_back.api.dtos.User;
import com.plan2go.viajes_back.api.register.UserRegister;
import com.plan2go.viajes_back.entity.UserEntity;
import com.plan2go.viajes_back.mappers.UserMapper;
import com.plan2go.viajes_back.repository.UserRepository;
import com.plan2go.viajes_back.repository.model.UserConfidential;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

    @Override
    @Transactional(readOnly = true)
    public UserConfidential findByEmailWithPassword(String email) {
            TypedQuery<UserEntity> sqlConsulta = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
            sqlConsulta.setParameter("email", email.trim().toLowerCase());
            sqlConsulta.setMaxResults(1);
            List<UserEntity> list = sqlConsulta.getResultList();
            return list.isEmpty() ? null : userMapper.toUserConfidential(list.get(0));
    }

    @Override
    public List<User> findByEmail(String email) {
        String sqlConsulta = "SELECT u FROM UserEntity u WHERE u.email LIKE :email";
        Query query = (Query) entityManager.createQuery(sqlConsulta, UserEntity.class);
        query.setParameter("email", "%" + email.trim().toLowerCase() + "%");
        List<UserEntity> userEntities = query.getResultList();
        if(userEntities.isEmpty()){
            return null;
        }
        return userMapper.toUserList(userEntities);
    }

}

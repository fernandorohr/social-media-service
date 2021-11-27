package com.fernando.socialmediaservice.user.repository;

import com.fernando.socialmediaservice.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}

package com.shurankain.planning.app.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shurankain.planning.app.security.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String userName);
}

package com.example.interns.repository;

import java.util.Optional;

import com.example.interns.model.ERole;
import com.example.interns.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
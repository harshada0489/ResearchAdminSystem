package com.ras.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.ERole;
import com.ras.model.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
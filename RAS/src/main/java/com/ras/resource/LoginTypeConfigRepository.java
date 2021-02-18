package com.ras.resource;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ras.model.LoginTypeConfig;

@RepositoryRestResource(collectionResourceRel="LoginTypeConfig",path="logintype")
public interface LoginTypeConfigRepository extends MongoRepository<LoginTypeConfig,Integer>{

}

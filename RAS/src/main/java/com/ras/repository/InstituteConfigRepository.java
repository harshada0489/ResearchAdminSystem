package com.ras.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ras.model.InstituteConfig;


@RepositoryRestResource(collectionResourceRel="InstituteConfig",path="inst")
public interface InstituteConfigRepository extends MongoRepository<InstituteConfig, Integer>{

}

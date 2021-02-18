package com.ras.resource;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ras.model.DepartmentConfig;

@RepositoryRestResource(collectionResourceRel="DepartmentConfig",path="dept")
public interface DepartmentConfigRepository extends MongoRepository<DepartmentConfig,Integer> {

}

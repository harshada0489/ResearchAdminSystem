package com.ras.resource;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ras.model.Research;

@RepositoryRestResource(collectionResourceRel="research",path="register")
public interface ResearchRepository extends MongoRepository<Research,Integer>{
 
//	public List<Research> findByFirstName(String firstName);
	
}

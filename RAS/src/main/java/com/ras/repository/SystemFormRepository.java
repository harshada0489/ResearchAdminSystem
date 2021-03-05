package com.ras.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.SystemForm;

public interface SystemFormRepository extends MongoRepository<SystemForm, String>{

	
}

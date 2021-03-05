package com.ras.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.SystemForm;
import com.ras.model.User;

public interface SystemFormRepository extends MongoRepository<SystemForm, String>{

	Optional<SystemForm> findByFormName(String formName);
	
}

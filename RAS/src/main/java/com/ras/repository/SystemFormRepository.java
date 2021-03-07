package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.SystemForm;
import com.ras.model.User;

public interface SystemFormRepository extends MongoRepository<SystemForm, String>{

	Optional<SystemForm> findById(String id);
	
	Optional<SystemForm> findByFormName(String formName);
	
	List<SystemForm> findByStatus(String status);
	
}

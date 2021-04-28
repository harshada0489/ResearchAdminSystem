package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ras.model.SystemForm;
import com.ras.model.User;

public interface SystemFormRepository extends MongoRepository<SystemForm, Integer>{

	Optional<SystemForm> findById(String id);
	
	Optional<SystemForm> findByFormName(String formName);
	
	List<SystemForm> findByStatusOrderByIdDesc(String status);
	
	List<SystemForm> findByFilterListOrderByIdDesc(String filterList);
	
//	@Query("{ state:'ACTIVE' }")
//	SystemForm findOneActive(Sort sort);
}

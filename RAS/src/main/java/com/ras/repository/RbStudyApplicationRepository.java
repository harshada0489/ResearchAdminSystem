package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.RbStudyApplication;

public interface RbStudyApplicationRepository extends MongoRepository<RbStudyApplication, Integer>{

	
	Optional<RbStudyApplication> findByStudyTitle(String studyTitle);
	
	List<RbStudyApplication> findByCreatorId(Integer creatorId);
	
}

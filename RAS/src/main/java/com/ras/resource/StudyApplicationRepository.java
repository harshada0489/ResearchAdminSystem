package com.ras.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.ras.model.StudyApplication;

public interface StudyApplicationRepository extends MongoRepository<StudyApplication, Integer> {

	Optional<StudyApplication> findByStudyTitle(String studyTitle);
	
	
}

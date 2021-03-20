package com.ras.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ras.model.CreateStudy;

public interface CreateStudyRepository extends MongoRepository<CreateStudy, String> {

	Optional<CreateStudy> findByStudyTitle(String studyTitle);
	
}

package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.LabelDetails;
import com.ras.model.User;

public interface LabelDetailsRepository extends MongoRepository<LabelDetails, String> {
	List<LabelDetails> findByStudyTypeAndStudyField(String studyType, String studyField);
	
}

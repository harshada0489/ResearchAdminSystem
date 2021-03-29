package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.Question;
import com.ras.model.User;

public interface QuestionRepository extends MongoRepository<Question, Integer> {
	List<Question> findByFormIdAndPageNumber(Integer formId, String pageNumber);
	
	List<Question> findByFormId(Integer formId);
	
}

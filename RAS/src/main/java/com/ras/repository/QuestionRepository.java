package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.Question;
import com.ras.model.User;

public interface QuestionRepository extends MongoRepository<Question, String> {
	List<Question> findByFormIdAndPageNumber(String formId, String pageNumber);
	
}

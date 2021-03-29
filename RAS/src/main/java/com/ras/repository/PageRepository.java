package com.ras.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.Page;
import com.ras.model.Question;

public interface PageRepository extends MongoRepository<Page, Integer>{
	
	Page findByFormIdAndPageNumber(int formId, int pageNumber);
}

package com.ras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.repository.QuestionRepository;
import com.ras.repository.StudyDataFormRepository;

@Service
public class StudyDataFormService {

	@Autowired
	StudyDataFormRepository repository;
	
	public StudyDataFormService() {
		// TODO Auto-generated constructor stub
	}

}

package com.ras.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.CreateStudy;
import com.ras.model.Question;
import com.ras.model.payload.request.LoginRequest;
import com.ras.repository.QuestionRepository;
import com.ras.resource.CreateStudyRepository;
//import com.ras.resource.LoginRequestRepository;

@Service
public class CreateStudyService {

	@Autowired
	CreateStudyRepository createStudyRepository;
	
	@Autowired
	QuestionRepository repository;
	
	public CreateStudyService() {
		// TODO Auto-generated constructor stub
	}

	public List<Question> addCreateStudyDefaultValues(CreateStudy createStudy) {
		createStudy.setCreatedDate(new java.util.Date());
		createStudy.setModifiedDate(new java.util.Date());
		createStudy.setStatus("pending");
		System.out.println("Calling from addCreateStudyDefaultValues()" + createStudy);
		
		
		String studyField = createStudy.getSelectedStudyField();
		String studyType =  createStudy.getSelectedStudyOption();
		List<Question> list = new ArrayList<Question>();
//		List<Question> list=repository.findByStudyTypeAndStudyField(studyType, studyField);
		if(list.isEmpty()) {
			return list;
		}

		createStudyRepository.save(createStudy);
		return list;
		
	}
}

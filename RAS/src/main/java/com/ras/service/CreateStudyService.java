package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.CreateStudy;
import com.ras.model.LabelDetails;
import com.ras.model.payload.request.LoginRequest;
import com.ras.repository.LabelDetailsRepository;
import com.ras.resource.CreateStudyRepository;
//import com.ras.resource.LoginRequestRepository;

@Service
public class CreateStudyService {

	@Autowired
	CreateStudyRepository createStudyRepository;
	
	@Autowired
	LabelDetailsRepository lbRepository;
	
	public CreateStudyService() {
		// TODO Auto-generated constructor stub
	}

	public List<LabelDetails> addCreateStudyDefaultValues(CreateStudy createStudy) {
		createStudy.setCreatedDate(new java.util.Date());
		createStudy.setModifiedDate(new java.util.Date());
		createStudy.setStatus("pending");
		System.out.println("Calling from addCreateStudyDefaultValues()" + createStudy);
		
		
		String studyField = createStudy.getSelectedStudyField();
		String studyType =  createStudy.getSelectedStudyOption();
		List<LabelDetails> list=lbRepository.findByStudyTypeAndStudyField(studyType, studyField);
		if(list.isEmpty()) {
			return list;
		}

		createStudyRepository.save(createStudy);
		return list;
		
	}
}

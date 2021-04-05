package com.ras.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.RbStudyApplication;
import com.ras.model.StudyApplication;
import com.ras.repository.RbStudyApplicationRepository;
import com.ras.service.mongodbOperations.NextSequenceService;


@Service
public class RbStudyApplicationService {

	@Autowired
	RbStudyApplicationRepository repository;

	@Autowired
	NextSequenceService nextSequenceService;
	
	public void addRvStudyApp(StudyApplication studyAppId) {
		
		RbStudyApplication rbStudyApp = new RbStudyApplication(studyAppId.getStudyTitle(),  
				studyAppId.getOneWordIdentifier(), studyAppId.getGender(),  studyAppId.getAge(),
				studyAppId.getNoOfHumanRequired(),  studyAppId.getTypeOfAnimal(),  
				studyAppId.getNoOfAnimalRequired(), studyAppId.getFilter1(),  studyAppId.getFilter2(), 
				studyAppId.getResearcherID(), studyAppId.getCreatorId(), -1, "");
		rbStudyApp.setStatus("active");
		rbStudyApp.setCreatedDate(new Date());
		rbStudyApp.setModifiedDate(new Date());
		
		int seq = nextSequenceService.getNextSequenceForPageId("customSequences");
		System.out.println("dynamicTableId generated" + seq);
		rbStudyApp.setId(seq);
		
		
		repository.save(rbStudyApp);
			
		
		
	}
}

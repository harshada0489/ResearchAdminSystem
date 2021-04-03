package com.ras.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ras.model.StudyContacts;
import com.ras.repository.StudyContactsRepository;
import com.ras.service.mongodbOperations.NextSequenceService;


@Service
public class StudyContactsService {

	@Autowired
	StudyContactsRepository repository;
	
	@Autowired
	NextSequenceService nextSequenceService;
	

	public void saveStudyContacts(List<StudyContacts> studyContactsList,Integer studyId, Integer studyDataFormId) {
		System.out.println("Inside StudyContactsService method: saveStudyContacts()");
		System.out.println("list studyContacts size  = " + studyContactsList.size());
		
		for(int curr = 0; curr<studyContactsList.size(); curr++) {
			StudyContacts studyContacts = studyContactsList.get(curr);
			
			studyContacts.setStudyAppId(studyId);
			studyContacts.setStudyDataFormId(studyDataFormId);
			
			studyContacts.setCreatedDate(new Date());
			studyContacts.setModifiedDate(new Date());
			studyContacts.setStatus("active");
			studyContacts.setRvId(-1);
			

			int seq = nextSequenceService.getNextSequenceForStudyContactsId("customSequences");
			System.out.println("dynamic StudyContactsId generated = " + seq);
			
			studyContacts.setId(seq);
			
			
			
			repository.save(studyContacts);
		}
		
		
		
	}
}

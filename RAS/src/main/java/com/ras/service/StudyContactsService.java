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
import com.ras.model.StudyContactsConfig;
import com.ras.repository.StudyContactsConfigRepository;
import com.ras.repository.StudyContactsRepository;
import com.ras.service.mongodbOperations.NextSequenceService;


@Service
public class StudyContactsService {

	@Autowired
	StudyContactsRepository repository;
	
	@Autowired
	StudyContactsConfigRepository studyContactsConfigRepository;
	
	@Autowired
	NextSequenceService nextSequenceService;
	

	public void saveStudyContacts(List<StudyContacts> studyContactsList,Integer studyId, Integer studyDataFormId, Integer creatorId) {
		System.out.println("Inside StudyContactsService method: saveStudyContacts()");
		System.out.println("list studyContacts size  = " + studyContactsList.size());
		
		for(int curr = 0; curr<studyContactsList.size(); curr++) {
			StudyContacts studyContacts = studyContactsList.get(curr);
			
			studyContacts.setStudyAppId(studyId);
			studyContacts.setStudyDataFormId(studyDataFormId);
			studyContacts.setCreatorId(creatorId);
			
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
	
	public List<StudyContactsConfig> getUserTypes() {
		List<StudyContactsConfig> tList = studyContactsConfigRepository.findAll();
		return tList;
	}
	
	public StudyContacts getStudyContactByType(Integer studyAppId, int type ) {
		StudyContacts studyContact = repository.findByStudyAppIdAndType(studyAppId, type);
		return studyContact;
		
	}
	
	public List<StudyContacts> getStudyContactByStudyDataFormId(int studyDataFormId){
		
		 List<StudyContacts> studyContactsList = repository.findByStudyDataFormId(studyDataFormId);
		
		return studyContactsList;
	}
	
}

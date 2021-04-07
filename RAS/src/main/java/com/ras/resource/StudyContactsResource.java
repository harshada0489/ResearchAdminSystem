package com.ras.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.StudyApplication;
import com.ras.model.StudyContacts;
import com.ras.service.StudyContactsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class StudyContactsResource {

	@Autowired
	private StudyContactsService studyContactsService;
	

	@PostMapping("/study/{creatorId}/{studyId}/{studyAppDataId}/{studyDataFormId}/contactDetails")
	public ResponseEntity<?> contactDetailsList(
			@PathVariable Integer creatorId,@PathVariable Integer studyId, 
			@PathVariable Integer studyAppDataId, @PathVariable Integer studyDataFormId,
			@RequestBody  List<StudyContacts> studyContacts) {
		System.out.println("Inside class:StudyContactsService method: contactDetailsList()");
		ResponseEntity<?> responseMap;
		System.out.println("studyContacts =" + studyContacts);
		
		System.out.println("studyId =" + studyId);
		System.out.println("studyAppDataId =" + studyAppDataId);
		System.out.println("studyDataFormId =" + studyDataFormId);
		
		studyContactsService.saveStudyContacts(studyContacts,studyId, studyDataFormId, creatorId);
		
		
		
	return ResponseEntity.ok("Successful");
	}
}

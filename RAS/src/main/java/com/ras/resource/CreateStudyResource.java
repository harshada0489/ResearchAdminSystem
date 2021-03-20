package com.ras.resource;

import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.ras.model.CreateStudy;
import com.ras.model.Question;
import com.ras.model.payload.request.LoginRequest;
import com.ras.service.CreateStudyService;
//import com.ras.service.LoginRequestService;
import com.ras.service.MongoListCollections;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class CreateStudyResource {
	
	@Autowired
	private CreateStudyService createStudyService;
	
	@PostMapping(path="/studyForm")
	public ResponseEntity<?> addUserRequest(@RequestBody CreateStudy createStudy) {
		HashMap<String, String> hmap = new HashMap<>();
		System.out.println("calling from ---------->>>>>> class: CreateStudyResource , method:addUserRequest ");
		System.out.println("Response Body params ---------->>>>> " + createStudy);
		String savedCreateForm = createStudyService.addCreateStudyDefaultValues(createStudy);
		
		if(savedCreateForm.equalsIgnoreCase("success")) {
			 String studyTitle = createStudy.getStudyTitle();
			 String oneWordIdentifier = createStudy.getOneWordIdentifier();
			  hmap = createStudyService.getStudyForm(studyTitle, oneWordIdentifier);
			 System.out.println("hmap = " + hmap);
		}
		
	return ResponseEntity.ok(hmap);
	}

	

	@PostMapping(path="/study/{studyId}/{filterFormId}/{systemFormId}/{page}")
	public ResponseEntity<?> provideQuestionList(@PathVariable String studyId, @PathVariable String filterFormId, @PathVariable String systemFormId) {
		System.out.println("Inside provideQuestionList()");
		
	return ResponseEntity.ok("successfully requested to backend");
	}
	
}

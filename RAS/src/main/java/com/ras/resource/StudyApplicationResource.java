package com.ras.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.ras.model.Question;
import com.ras.model.StudyApplication;
import com.ras.model.payload.request.LoginRequest;
//import com.ras.service.LoginRequestService;
import com.ras.service.MongoListCollections;
import com.ras.service.StudyApplicationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class StudyApplicationResource {
	
	@Autowired
	private StudyApplicationService studyApplicationService;
	
	@PostMapping("/studyForm")
	public ResponseEntity<?> addUserRequest(@RequestBody StudyApplication createStudy) {
		
		System.out.println("calling from ---------->>>>>> class: CreateStudyResource , method:addUserRequest ");
//		System.out.println("Response Body params ---------->>>>> " + createStudy);
//		String savedCreateForm = studyApplicationService.addCreateStudyDefaultValues(createStudy);
//		
//		if(savedCreateForm.equalsIgnoreCase("success")) {
//			 String studyTitle = createStudy.getStudyTitle();
//			 String oneWordIdentifier = createStudy.getOneWordIdentifier();
//			  hmap = studyApplicationService.getStudyForm(studyTitle, oneWordIdentifier);
//			 System.out.println("hmap = " + hmap);
//		}
		List<Question> qList = new ArrayList<>();
		Question q1 = new Question("70","1","1","What is your name?" ,"text", "name", "","101");
		qList.add(q1);
		Question q2 = new Question("70","1","2","What is your age?" ,"text", "age", "","101");
		qList.add(q2);
		Question q3 = new Question("70","1","3","What is your dob?" ,"text", "dob", "","101");
		qList.add(q3);
		
		
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		for(int count = 0 ; count< qList.size(); count++) {
			HashMap<String, String> hmap = new HashMap<>();
			
			hmap.put("studyDataId", "345");		
			hmap.put("studyId", "25");
			
			hmap.put("page", qList.get(count).getPageNumber());
			hmap.put("systemFormId", qList.get(count).getFormId());
			hmap.put("questionNumber", qList.get(count).getQuestionNumber());
			hmap.put("questionText", qList.get(count).getQuestionText());
			hmap.put("answerType", qList.get(count).getAnswerType());
			hmap.put("dbColumnName", qList.get(count).getDbColumnName());
			
			
			questionList.add(hmap);
			
		}
		
		
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		responseMap.put("questionList", questionList);

		
		
		int countOfQuestionPages = 5;
		
		
		
		
		
		responseMap.put("pageList", countOfQuestionPages); //add pagelist later todo
		
		
		
	
	return ResponseEntity.ok(responseMap);
	}

	

	@GetMapping("/study/{studyId}/{filterFormId}/{systemFormId}/{page}")
	public ResponseEntity<?> provideQuestionList(@PathVariable String studyId, @PathVariable String filterFormId, @PathVariable String systemFormId, @PathVariable String page) {
		System.out.println("Inside provideQuestionList()");
		
		
		System.out.println("systemFormId =" + systemFormId);
		System.out.println("page = " + page);
		
//		createStudyService.findQuestionForPage(systemFormId, page);
		
		
	return ResponseEntity.ok("successfully requested to backend");
	}
	
	
	
}

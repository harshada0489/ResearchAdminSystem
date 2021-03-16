package com.ras.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.swing.text.Document;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ras.model.payload.response.JwtResponse;

import com.ras.model.Question;
import com.ras.model.payload.request.LoginRequest;
import com.ras.model.payload.response.JwtResponse;
import com.ras.repository.UserRepository;
import com.ras.service.MongoListCollections;
import com.ras.service.QuestionService;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientSettings;

import java.util.Arrays;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class QuestionResource {

//	@Autowired
//	AuthenticationManager authenticationManager;

	
	@Autowired
	QuestionService service;
	
	boolean isEndForm= false;
	
	@PostMapping("/questionDetails/{formId}/Page/{pageNumber}")
	public ResponseEntity<?> addQuestionDetails(@RequestBody List<Question> question ,@PathVariable String formId , @PathVariable int pageNumber, boolean isEndForm) {
		
		HashMap<String, String> returnMap = new HashMap<String,String>();
		
		System.out.println("Inside class: QuestionResource , && method: addQuestionDetails()");
		System.out.println("question ====== " + question.toString());

		String msg = service.addQuestionDetails(question);
		if(msg.equalsIgnoreCase("Successful")) {
			returnMap.put("message", "success");
			returnMap.put("formId", formId);
			returnMap.put("pageId", "102");
			
			if(!(isEndForm)) {
				int updatePagenumber = pageNumber + 1;
				
				returnMap.put("path", "/"+formId + "/cre"
						+ "ateQuestion/Page/"+updatePagenumber);
				returnMap.put("pageNumber", updatePagenumber+"");
				
			}else {
				returnMap.put("path", "/"+formId + "/createQuestion/Page/"+pageNumber);
				returnMap.put("pageNumber", pageNumber+"");
			}
		}
		
		return ResponseEntity.ok(returnMap);
		
	}

	
	@PostMapping("/questionDetails/endForm/{formId}/Page/{pageNumber}")
	public ResponseEntity<?> endQuestionDetails(@RequestBody List<Question> question, @PathVariable String formId , @PathVariable int pageNumber) {
		System.out.println("Inside class: QuestionResource , && method: endQuestionDetails()");
		ResponseEntity<?> reponse = addQuestionDetails(question, formId, pageNumber, true);
		System.out.println("reponse = "+ reponse);
		System.out.println("question ====== " + question.toString());

		System.out.println("-----------------Before Monngo db collection---------------- " );
		
		MongoListCollections.createCollection(formId);
		System.out.println("-----------------After Monngo db collection---------------- " );
//		service.addQuestionDetails(question);		
		
		return ResponseEntity.ok("success from end form");
		
	}
	
	
	@PostMapping("/questionDetails/goToBackPage")
	public ResponseEntity<?> goToPreviousQuestionDetailsPage(@RequestBody List<Question> question) {
		System.out.println("Inside class: QuestionResource , && method: goToPreviousQuestionDetailsPage()");
		System.out.println("question ====== " + question.toString());

		HashMap<String, String> returnMap = new HashMap<String,String>();
		
		String msg = "Successful";
		if(msg.equalsIgnoreCase("Successful")) {
			
			
//			int updatePagenumber = pageNumber - 1;
			returnMap.put("message", "success");
//			returnMap.put("path", "/"+formId + "/createQuestion/Page/"+updatePagenumber);
			
//			returnMap.put("formId", formId);
			returnMap.put("pageId", "102");
//			returnMap.put("pageNumber", updatePagenumber+"");
		}
		
		return ResponseEntity.ok(returnMap);
		
	}
	
	

	
	
}

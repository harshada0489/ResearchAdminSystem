package com.ras.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ras.model.payload.response.JwtResponse;


import com.ras.model.Question;
import com.ras.model.payload.request.LoginRequest;
import com.ras.model.payload.response.JwtResponse;
import com.ras.repository.UserRepository;
import com.ras.service.QuestionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class QuestionResource {

//	@Autowired
//	AuthenticationManager authenticationManager;

	
	@Autowired
	QuestionService service;
	
	@PostMapping("/questionDetails/{formId}/Page/{pageNumber}")
	public ResponseEntity<?> addQuestionDetails(@RequestBody List<Question> question ,@PathVariable String formId , @PathVariable String pageNumber) {
		System.out.println("Inside class: QuestionResource , && method: addQuestionDetails()");
		System.out.println("question1 ====== " + question.toString());
//		System.out.println("question2 ====== " + question2.toString());
		System.out.println("formId ====== " + formId);
		System.out.println("pageNumber ====== " + pageNumber);
//		System.out.println("bodyFormId ====== " + bodyFormId);
		
		
		service.addQuestionDetails(question);
		
		return ResponseEntity.ok("success");
		
	}

}

package com.ras.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.CreateStudy;
import com.ras.model.Question;
import com.ras.model.payload.request.LoginRequest;
import com.ras.service.CreateStudyService;
//import com.ras.service.LoginRequestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class CreateStudyResource {
	
	@Autowired
	private CreateStudyService createStudyService;
	
	@PostMapping(path="/studyForm")
	public List<Question> addUserRequest(@RequestBody CreateStudy createStudy) {
		System.out.println("calling from ---------->>>>>> class: CreateStudyResource , method:addUserRequest ");
		System.out.println("Response Body params ---------->>>>> " + createStudy);
		
		List<Question> returnValue = createStudyService.addCreateStudyDefaultValues(createStudy);
//		Response Body params ---------->>>>> CreateStudy [id=null, studyTitle=fgh, oneWordIdentifier=dfgh, gender=null, age=27, NoOfHumanRequired=6, typeOfAnimal=, NoOfAnimalRequired=0, selectedStudyField=Diabeties, researcherID=[], status=null, createdDate=null, modifiedDate=null]	
	return returnValue;
	}

}

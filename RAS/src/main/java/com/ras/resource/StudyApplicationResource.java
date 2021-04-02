package com.ras.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.ras.model.StudyDataForm;
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
	public ResponseEntity<?> addUserRequest(@RequestBody StudyApplication studyApplication) throws Exception {
		
//		HashMap<String, String> hmap = new HashMap<>();
//		System.out.println("calling from ---------->>>>>> class: CreateStudyResource , method:addUserRequest ");
//		System.out.println("Response Body params ---------->>>>> " + studyApplication);
//		Integer studyAppId = 0;
//		 studyAppId = studyApplicationService.addCreateStudyDefaultValues(studyApplication);
//		Integer systemFormDataId = null;
//		Integer dynamicTableDataId = null;
//		Integer studyDataFormId = null;
//		if(studyAppId != 0) {
//			 
//			hmap= studyApplicationService.searchForfilterList(studyAppId);
//			systemFormDataId = studyApplicationService.putEntryInStudyDataForm(hmap);
//			System.out.println("systemFormDataId = " + systemFormDataId);
//			
//			StudyDataForm studyDataForm = studyApplicationService.getStudyDataApp(systemFormDataId);
//			System.out.println("studyDataForm = " + studyDataForm.getDynamicTableName() + " , " + 
//					studyDataForm.getId());
//			
//			
//			String dynamicTableName = studyDataForm.getDynamicTableName();
//			 studyDataFormId = studyDataForm.getId();
//			 System.out.println("studyDataFormId = " + studyDataFormId);
//			Integer systemFormId = studyDataForm.getSystemFormId();
//			dynamicTableDataId = studyApplicationService.updateDynamicTable(dynamicTableName, studyDataFormId, systemFormId);
//			
//			System.out.println("dynamicTableDataId = "+ dynamicTableDataId);
//			
//			studyApplicationService.updateStudyDataFormWithDynamicId(studyDataForm, dynamicTableDataId);
//
//		}
		
		
		Map<Integer,Object> userList = new HashMap<Integer,Object>();
		userList.put(1, "Harshada Bhangale");
		userList.put(2, "Rahul Bhole");
		userList.put(3, "Yuga");
		
		Map<Integer,Object> typeList = new HashMap<Integer,Object>();
		typeList.put(1, "Principal Investigator");
		typeList.put(2, "Study Author");
		typeList.put(3, "Study Contact");
		typeList.put(4, "Reviewer");
		
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		
		responseMap.put("dynamicTableDataId", 25);
		responseMap.put("userList", userList);
		responseMap.put("typeList", typeList);
		responseMap.put("studyDataFormId", 45);
		responseMap.put("studyAppId", 20);

		System.out.println("responseMap =" + responseMap);
	return ResponseEntity.ok(responseMap);
	}	
	
	
	
	
	@PostMapping("/studyForm/showPage1")
	public ResponseEntity<?> showPage1(@RequestBody StudyApplication studyApplication) throws Exception {
		List<HashMap<String,String>> questionList = new ArrayList<>();
		HashMap<String, String> hmap = new HashMap<>();
		System.out.println("calling from ---------->>>>>> class: CreateStudyResource , method:addUserRequest ");
		System.out.println("Response Body params ---------->>>>> " + studyApplication);
		Integer studyAppId = 0;
		 studyAppId = studyApplicationService.addCreateStudyDefaultValues(studyApplication);
		Integer systemFormDataId = null;
		Integer dynamicTableDataId = null;
		Integer studyDataFormId = null;
		if(studyAppId != 0) {
			 
			hmap= studyApplicationService.searchForfilterList(studyAppId);
			systemFormDataId = studyApplicationService.putEntryInStudyDataForm(hmap);
			System.out.println("systemFormDataId = " + systemFormDataId);
			
			StudyDataForm studyDataForm = studyApplicationService.getStudyDataApp(systemFormDataId);
			System.out.println("studyDataForm = " + studyDataForm.getDynamicTableName() + " , " + 
					studyDataForm.getId());
			
			
			String dynamicTableName = studyDataForm.getDynamicTableName();
			 studyDataFormId = studyDataForm.getId();
			 System.out.println("studyDataFormId = " + studyDataFormId);
			Integer systemFormId = studyDataForm.getSystemFormId();
			dynamicTableDataId = studyApplicationService.updateDynamicTable(dynamicTableName, studyDataFormId, systemFormId);
			
			System.out.println("dynamicTableDataId = "+ dynamicTableDataId);
			
			studyApplicationService.updateStudyDataFormWithDynamicId(studyDataForm, dynamicTableDataId);
			
			String pageNumber = "1";
			questionList = studyApplicationService.getStudyFormByPageId(pageNumber ,studyDataForm);

		}
		
		
		
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		responseMap.put("questionList", questionList);
		responseMap.put("dynamicTableDataId", dynamicTableDataId+"");
		int countOfQuestionPages= studyApplicationService.getTheCountOfQuestionPages(questionList.get(0).get("systemFormId"));	

		responseMap.put("pageList", countOfQuestionPages); //add pagelist later todo
		
		System.out.println("responseMap =" + responseMap);
	return ResponseEntity.ok(responseMap);
	}

	

	@GetMapping("/study/{studyId}/{filterFormId}/{systemFormId}/{page}")
	public ResponseEntity<?> provideQuestionList(@PathVariable String studyId, @PathVariable String filterFormId, @PathVariable String systemFormId, @PathVariable String page) {
		System.out.println("Inside class:StudyApplicationResource method: provideQuestionList()");		
		
		System.out.println("systemFormId =" + systemFormId);
		System.out.println("page = " + page);
		
	return ResponseEntity.ok("successfully requested to backend");
	}
	
	
	@PostMapping("/study/{currPage}/goToNextPage")
	public ResponseEntity<?> getNextPageQuestionList(@PathVariable Integer currPage, @RequestBody HashMap<String, String> answerList) {
		System.out.println("Inside class:StudyApplicationResource method: getNextPageQuestionList()");
		Map<String,Object> responseMap = new HashMap<String,Object>();
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		System.out.println("answerList =" + answerList);
		
		System.out.println("currPage =" + currPage);
		
		StudyDataForm studyDataForm = null;
		
		Iterator<String> ansIterator = answerList.keySet().iterator();

				String studyDataFormId= answerList.get("studyDataFormId");
				
				studyDataForm = studyApplicationService.searchforDataId(studyDataFormId);

				String dynamicTableName = studyDataForm.getDynamicTableName();
				Integer dynamicTableDataId = studyDataForm.getDynamicTableDataId();
				
				
				HashMap<String, Object> dbColumnNamesAnswerList = new HashMap<>();
				
				for(String key: answerList.keySet()) {
					if(key.contains("studyId")) {
						dbColumnNamesAnswerList.put(key, answerList.get(key));
					}
					if(!(key.endsWith("Id"))) {
						System.out.println(key);
						dbColumnNamesAnswerList.put(key, answerList.get(key));
					}
				}
				
				studyApplicationService.calldynamicTableService(dynamicTableName,dynamicTableDataId,dbColumnNamesAnswerList);
				
		//after successful insertion of data 
		Integer nextPage = currPage + 1;
		Integer systemFormId = studyDataForm.getSystemFormId();
		
		String nextPageString = nextPage.toString();
		questionList = studyApplicationService.getStudyFormByPageId(nextPageString, studyDataForm);
		
		responseMap.put("questionList", questionList);
		if(!(questionList.isEmpty())) {
			int countOfQuestionPages= studyApplicationService.getTheCountOfQuestionPages(questionList.get(0).get("systemFormId"));	

			responseMap.put("pageList", countOfQuestionPages);
		}
		

	return ResponseEntity.ok(responseMap);
	}

	
	@PostMapping("/study/{currPage}/endPage")
	public ResponseEntity<?> endQuestionList(@PathVariable Integer currPage, @RequestBody HashMap answerList) {
		System.out.println("Inside class:StudyApplicationResource method: endQuestionList()");
		ResponseEntity<?> responseMap;
		System.out.println("answerList =" + answerList);
		System.out.println("currPage =" + currPage);
		getNextPageQuestionList(currPage,answerList);


		
		
	return ResponseEntity.ok("Successful");
	}
	
	
	@PostMapping("/study/{studyId}/{studyAppDataId}/{studyDataFormId}/contactDetails")
	public ResponseEntity<?> contactDetailsList(
			@PathVariable Integer studyId, @PathVariable Integer studyAppDataId, @PathVariable Integer studyDataFormId,
			@RequestBody  List<Object> contactList) {
		System.out.println("Inside class:StudyApplicationResource method: contactDetailsList()");
		ResponseEntity<?> responseMap;
		System.out.println("contactList =" + contactList);
		
		System.out.println("studyId =" + studyId);
		System.out.println("studyAppDataId =" + studyAppDataId);
		System.out.println("studyDataFormId =" + studyDataFormId);
		

		
		
	return ResponseEntity.ok("Successful");
	}
	
	
}

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
			
			questionList = studyApplicationService.getStudyForm(studyDataForm);

		}
		
		
		
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		responseMap.put("questionList", questionList);
		responseMap.put("dynamicTableDataId", dynamicTableDataId+"");
//		responseMap.put("studyDataFormId", studyDataFormId+"");
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
		// search for studyDataId tin qList to insert the values in that id's form 
		
		StudyDataForm studyDataForm = null;
		
		Iterator<String> ansIterator = answerList.keySet().iterator();
//		while(ansIterator.hasNext()) {
//			String key = ansIterator.next();
//			if(key == "studyDataFormId") {
				String studyDataFormId= answerList.get("studyDataFormId");
				
				studyDataForm = studyApplicationService.searchforDataId(studyDataFormId);
				
//				System.out.println("studyDataForm id retrieved = " + studyDataForm.getId());
				String dynamicTableName = studyDataForm.getDynamicTableName();
				Integer dynamicTableDataId = studyDataForm.getDynamicTableDataId();
				
				
				HashMap<String, Object> dbColumnNamesAnswerList = new HashMap<>();
				
				for(String key: answerList.keySet()) {
					if(!(key.endsWith("Id"))) {
						System.out.println(key);
						dbColumnNamesAnswerList.put(key, answerList.get(key));
					}
				}
				
				studyApplicationService.calldynamicTableService(dynamicTableName,dynamicTableDataId,dbColumnNamesAnswerList);
				
				
//		}
		
		
		//after successful insertion of data 
		Integer nextPage = currPage + 1;
		Integer systemFormId = studyDataForm.getSystemFormId();
		
		String nextPageString = nextPage.toString();
		questionList = studyApplicationService.getStudyFormByPageId(nextPageString, studyDataForm);
		
		
//		List<Question> qList = new ArrayList<>();
//		Question q1 = new Question("70",nextPageString ,"1","Mother's Name?" ,"text", "name", "","101");
//		qList.add(q1);
//		Question q2 = new Question("70",nextPageString,"2","Father's Name?" ,"text", "age", "","101");
//		qList.add(q2);
//		Question q3 = new Question("70",nextPageString,"3","Sibling's Name(if any) ?" ,"text", "dob", "","101");
//		qList.add(q3);
//		
//		
//		List<HashMap<String,String>> questionList = new ArrayList<>();
//		
//		for(int count = 0 ; count< qList.size(); count++) {
//			HashMap<String, String> hmap = new HashMap<>();
//			
//			hmap.put("studyDataId", "345");		
//			hmap.put("studyId", "25");
//			
//			hmap.put("page", qList.get(count).getPageNumber());
//			hmap.put("systemFormId", qList.get(count).getFormId());
//			hmap.put("questionNumber", qList.get(count).getQuestionNumber());
//			hmap.put("questionText", qList.get(count).getQuestionText());
//			hmap.put("answerType", qList.get(count).getAnswerType());
//			hmap.put("dbColumnName", qList.get(count).getDbColumnName());
//			
//			questionList.add(hmap);
//			
//		}
		
		
		responseMap.put("questionList", questionList);

//		int countOfQuestionPages = 2;		
		int countOfQuestionPages= studyApplicationService.getTheCountOfQuestionPages(questionList.get(0).get("systemFormId"));	

		responseMap.put("pageList", countOfQuestionPages);
//		}
	return ResponseEntity.ok(responseMap);
	}

	
	@PostMapping("/study/{currPage}/endPage")
	public ResponseEntity<?> endQuestionList(@PathVariable Integer currPage, @RequestBody Map answerList) {
		System.out.println("Inside class:StudyApplicationResource method: endQuestionList()");
		
		System.out.println("answerList =" + answerList);
		System.out.println("currPage =" + currPage);
		// search for studyDataId tin qList to insert the values in that id's form 
		
		//after successful insertion of data 

		
		
	return ResponseEntity.ok("Successful");
	}
	
	
	
	
	
}

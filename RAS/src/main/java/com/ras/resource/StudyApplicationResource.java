package com.ras.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.Criteria;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.ras.model.DepartmentConfig;
import com.ras.model.Question;
import com.ras.model.RbStudyApplication;
import com.ras.model.StudyApplication;
import com.ras.model.StudyContacts;
import com.ras.model.StudyContactsConfig;
import com.ras.model.StudyDataForm;
import com.ras.model.SystemForm;
import com.ras.model.User;
import com.ras.model.payload.request.LoginRequest;
//import com.ras.service.LoginRequestService;
import com.ras.service.MongoListCollections;
import com.ras.service.RbStudyApplicationService;
import com.ras.service.StudyApplicationService;
import com.ras.util.SystemConstant;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class StudyApplicationResource {
	
	@Autowired
	private StudyApplicationService studyApplicationService;
	
	@Autowired
	private RbStudyApplicationService rbStudyApplicationService;
	
	
	@GetMapping("/viewMyStudyForm/{creatorId}")
	public ResponseEntity<?> getAllStudyApp(@PathVariable Integer creatorId ) {
		System.out.println("Inside class: StudyApplicationResource and method: getAllStudyApp() ");
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		List<StudyApplication> allForms = studyApplicationService.getAllStudyApp(creatorId);
		System.out.println("allForms =  " + allForms);
		
		
		List<RbStudyApplication> myTasks = studyApplicationService.getMyTask(creatorId);
		
		
		responseMap.put("MyStudyApp",allForms);
		responseMap.put("myTasks",myTasks);
		
		return ResponseEntity.ok(responseMap);
//		return allForms ;
	}

	@PostMapping("/studyForm")
	public ResponseEntity<?> addUserRequest(@RequestBody StudyApplication studyApplication) throws Exception {
		
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
			studyApplicationService.updateCurrentStudyDataForm(studyAppId, systemFormDataId);
			
			
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

		}
		
		
		List<User> userList= studyApplicationService.callForUserService();	
		
		List<StudyContactsConfig> typeList= studyApplicationService.callForStudyContactsConfigService();
	
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		System.out.println("request_user_list =" + userList);
		
//		responseMap.put("request_user_list",userList);
//		responseMap.put("request_type_list",typeList);		
		
		responseMap.put("dynamicTableDataId", dynamicTableDataId);
		responseMap.put("userList", userList);
		responseMap.put("typeList", typeList);
		responseMap.put("studyDataFormId", studyDataFormId);
		responseMap.put("studyAppId",studyAppId);

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

				String studyDataFormIdString= answerList.get("studyDataFormId");
				
				int studyDataFormId = Integer.parseInt(studyDataFormIdString);
				studyDataForm = studyApplicationService.searchforDataId(studyDataFormId);

				String dynamicTableName = studyDataForm.getDynamicTableName();
				Integer dynamicTableDataId = studyDataForm.getDynamicTableDataId();
				
				
				HashMap<String, Object> dbColumnNamesAnswerList = new HashMap<>();
				
				for(String key: answerList.keySet()) {
					if(key.contains("studyId") || key.contains("creatorId") ) {
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
		Map<String,Object> responseMap = new HashMap<String,Object>();
		System.out.println("answerList =" + answerList);
		System.out.println("currPage =" + currPage);
		getNextPageQuestionList(currPage,answerList);

		
		String studyAppIdString = answerList.get("studyId").toString();
		Integer studyAppId = Integer.parseInt(studyAppIdString);
		
		// locking the study
		studyApplicationService.callStudyDataFromServiceForLock(studyAppId);
		
	
		responseMap.put("load","Successful");
		responseMap.put("studyAppId",studyAppId);
		
	return ResponseEntity.ok(responseMap);
	}
	
	
	@PostMapping("/study/{studyAppId}/sendStudy/")
	public ResponseEntity<?> sendStudyAfterEndOfStudyForm(@PathVariable("studyAppId") Integer studyAppId) throws Exception {
		
		System.out.println("Inside class:StudyApplicationResource method: sendStudyAfterEndOfStudyForm()");
		Map<String,Object> responseMap = new HashMap<String,Object>();
		System.out.println("studyAppId =" + studyAppId);
		
		StudyContacts piContact = studyApplicationService.getStudyContactWithType(studyAppId, SystemConstant.TYPE_PRINCIPAL_INVESTIGATOR);
		if(piContact != null) {
			Integer creatorId = piContact.getCreatorId();
			Integer PIUserId = piContact.getUserId();
			
				int destinationRbId = -1; 
				int currentRbStudyAppId = 0;
				String reviewOutcome = null;
				String comments = null;
				rbStudyApplicationService.findAndSendRbStudyAppToNextState(studyAppId, SystemConstant.STATE_DRAFT, SystemConstant.STATE_PI, PIUserId, destinationRbId, currentRbStudyAppId, reviewOutcome, comments);
				System.out.println("Sent to Principal Investigator");
				String updateStatus = "Sent to Principal Investigator";
				studyApplicationService.callStudyAppServiceForUpdate(studyAppId,updateStatus);
			
		}
		

	return ResponseEntity.ok("Successful");
	}
	
	
	
	@PostMapping("/viewMyStudyForm/page/{currPage}/studyApp/view/{studyAppId}")
	public ResponseEntity<?> viewMyStudyApp(@PathVariable Integer currPage, @PathVariable Integer studyAppId){
		System.out.println("Inside class:StudyApplicationResource method: viewStudyApp()");
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		StudyDataForm studyDataForm = null;
		
				
				studyDataForm = studyApplicationService.searchforCurrentDataIdByStudyId(studyAppId);

				String dynamicTableName = studyDataForm.getDynamicTableName();
				Integer dynamicTableDataId = studyDataForm.getDynamicTableDataId();
				Integer studyId = studyDataForm.getStudyAppId();
				Integer creatorId = studyDataForm.getCreatorId();
				Boolean disabled = studyDataForm.isLock();
				
				
				HashMap<String, Object> dbColumnNamesAnswerList = new HashMap<>();
				dbColumnNamesAnswerList.put("studyId",studyId);
				dbColumnNamesAnswerList.put("creatorId",creatorId);
				dbColumnNamesAnswerList.put("studyId",studyId);
				
				
				
//				studyApplicationService.calldynamicTableService(dynamicTableName,dynamicTableDataId,dbColumnNamesAnswerList);
				
		//after successful insertion of data 
				

		Integer systemFormId = studyDataForm.getSystemFormId();
		
		
		String nextPageString = currPage.toString();
		
		questionList = studyApplicationService.getStudyFormByPageId(nextPageString, studyDataForm);
		
		responseMap.put("questionList", questionList);
		
		if(!(questionList.isEmpty())) {

			

//			HashSet<String> qDbColumnSet = new HashSet<>();
//			for(HashMap<String, String> q : questionList) {
//				System.out.println("q =" + q);
//				if(q.containsKey("dbColumnName")) {
//					
//					qDbColumnSet.add(q.get("dbColumnName"));
//				}
//			}
//			System.out.println("qDbColumnSet = "+ qDbColumnSet);
//			
			
			
			studyApplicationService.calldynamicTableServicefordbColumn(dynamicTableName,dynamicTableDataId, questionList);
			int countOfQuestionPages= studyApplicationService.getTheCountOfQuestionPages(questionList.get(0).get("systemFormId"));	

			responseMap.put("pageList", countOfQuestionPages);
			System.out.println("disabled =" + disabled);
			responseMap.put("disabled", disabled);
		}
		

	return ResponseEntity.ok(responseMap);

	}
	

	
	@PostMapping("/viewMyStudyForm/nextPage/{currPage}/studyApp/view/{studyAppId}")
	public ResponseEntity<?> viewNextPageStudyApp(@PathVariable Integer currPage, @PathVariable Integer studyAppId){
		System.out.println("Inside class:StudyApplicationResource method: viewStudyApp()");
		
		
		System.out.println("Inside class:StudyApplicationResource method: getNextPageQuestionList()");
		Map<String,Object> responseMap = new HashMap<String,Object>();
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		StudyDataForm studyDataForm = null;
		
				
				studyDataForm = studyApplicationService.searchforCurrentDataIdByStudyId(studyAppId);

				String dynamicTableName = studyDataForm.getDynamicTableName();
				Integer dynamicTableDataId = studyDataForm.getDynamicTableDataId();
				Integer studyId = studyDataForm.getStudyAppId();
				Integer creatorId = studyDataForm.getCreatorId();
				
				HashMap<String, Object> dbColumnNamesAnswerList = new HashMap<>();
				dbColumnNamesAnswerList.put("studyId",studyId);
				dbColumnNamesAnswerList.put("creatorId",creatorId);
				dbColumnNamesAnswerList.put("studyId",studyId);
				
				
				
//				studyApplicationService.calldynamicTableService(dynamicTableName,dynamicTableDataId,dbColumnNamesAnswerList);
				
		//after successful insertion of data 
				

		Integer systemFormId = studyDataForm.getSystemFormId();
		
		
		Integer nextPage = currPage + 1;
		String nextPageString = nextPage.toString();
		questionList = studyApplicationService.getStudyFormByPageId(nextPageString, studyDataForm);
		
		
		
		
		if(!(questionList.isEmpty())) {
			responseMap.put("questionList", questionList);
			
//			studyApplicationService.calldynamicTableServicefordbColumn(dynamicTableName,dynamicTableDataId);
			
			int countOfQuestionPages= studyApplicationService.getTheCountOfQuestionPages(questionList.get(0).get("systemFormId"));	

			responseMap.put("pageList", countOfQuestionPages);
		}
		

	return ResponseEntity.ok(responseMap);

	}
	
	
	
	@PostMapping("/viewMyStudyForm/saveDraftNextPage/{currPage}/studyApp/view/{studyAppId}")
	public ResponseEntity<?> viewAndSaveDraftedNextPageStudyApp(@PathVariable Integer currPage, @PathVariable Integer studyAppId,  @RequestBody HashMap<String, String> answerList){
		System.out.println("Inside class:StudyApplicationResource method: viewAndSaveDraftedNextPageStudyApp()");
		
		
		System.out.println("answeMapList = "+ answerList);
		Map<String,Object> responseMap = new HashMap<String,Object>();
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		StudyDataForm studyDataForm = null;
		
				
				studyDataForm = studyApplicationService.searchforCurrentDataIdByStudyId(studyAppId);

				String dynamicTableName = studyDataForm.getDynamicTableName();
				Integer dynamicTableDataId = studyDataForm.getDynamicTableDataId();
				Integer studyId = studyDataForm.getStudyAppId();
				Integer creatorId = studyDataForm.getCreatorId();
				
				HashMap<String, Object> dbColumnNamesAnswerList = new HashMap<>();
				
				for(String key: answerList.keySet()) {
					if(key.contains("studyId") || key.contains("creatorId") ) {
						dbColumnNamesAnswerList.put(key, answerList.get(key));
					}
					
					if(!(key.endsWith("Id"))) {
						System.out.println(key);
						dbColumnNamesAnswerList.put(key, answerList.get(key));
					}
				}
				
				
//				dbColumnNamesAnswerList.put("studyId",studyId);
//				dbColumnNamesAnswerList.put("creatorId",creatorId);
//				dbColumnNamesAnswerList.put("studyId",studyId);
				
				
				
				studyApplicationService.calldynamicTableService(dynamicTableName,dynamicTableDataId,dbColumnNamesAnswerList);
				
		//after successful insertion of data 
				

		Integer systemFormId = studyDataForm.getSystemFormId();
		
		
		Integer nextPage = currPage + 1;
		String nextPageString = nextPage.toString();
		questionList = studyApplicationService.getStudyFormByPageId(nextPageString, studyDataForm);
		
		
		
		
		if(!(questionList.isEmpty())) {
			responseMap.put("questionList", questionList);
			
//			studyApplicationService.calldynamicTableServicefordbColumn(dynamicTableName,dynamicTableDataId);
			
			int countOfQuestionPages= studyApplicationService.getTheCountOfQuestionPages(questionList.get(0).get("systemFormId"));	

			responseMap.put("pageList", countOfQuestionPages);
		}
		

	return ResponseEntity.ok(responseMap);

	}
	
	
	@PostMapping("/viewMyStudyForm/endDraftPage/{currPage}/studyApp/view/{studyAppId}")
	public ResponseEntity<?> viewAndendDraftPageStudyApp(@PathVariable Integer currPage, @PathVariable Integer studyAppId,  @RequestBody HashMap<String, String> answerList) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: viewAndSaveDraftedNextPageStudyApp()");
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		viewAndSaveDraftedNextPageStudyApp(currPage,studyAppId,answerList);
		
		studyApplicationService.callStudyDataFromServiceForLock(studyAppId);
		
		sendStudyAfterEndOfStudyForm(studyAppId);		
		
		responseMap.put("load","Successful");
		responseMap.put("studyAppId",studyAppId);

	return ResponseEntity.ok(responseMap);

	}
	

	
	
	
	
	
	
//	@PostMapping("/study/{studyId}/{studyAppDataId}/{studyDataFormId}/contactDetails")
//	public ResponseEntity<?> contactDetailsList(
//			@PathVariable Integer studyId, @PathVariable Integer studyAppDataId, @PathVariable Integer studyDataFormId,
//			@RequestBody  List<Object> contactList) {
//		System.out.println("Inside class:StudyApplicationResource method: contactDetailsList()");
//		ResponseEntity<?> responseMap;
//		System.out.println("contactList =" + contactList);
//		
//		System.out.println("studyId =" + studyId);
//		System.out.println("studyAppDataId =" + studyAppDataId);
//		System.out.println("studyDataFormId =" + studyDataFormId);
//		
//
//		
//		
//	return ResponseEntity.ok("Successful");
//	}
	
	
}

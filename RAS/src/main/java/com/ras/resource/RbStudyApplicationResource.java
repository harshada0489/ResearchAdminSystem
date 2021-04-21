	package com.ras.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.RbStudyApplication;
import com.ras.model.StudyDataForm;
import com.ras.service.RbStudyApplicationService;
import com.ras.service.StudyApplicationService;
import com.ras.service.StudyDataFormService;
import com.ras.util.SystemConstant;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class RbStudyApplicationResource {

	@Autowired
	private RbStudyApplicationService rbStudyApplicationService;
	
	@Autowired
	private StudyDataFormService studyDataFormService;
	
	
	@Autowired
	private StudyApplicationService studyApplicationService;
	
	@PostMapping("/viewMyRbTasksForm/page/{currPage}/studyApp/view/{rbStudyAppId}")
	public ResponseEntity<?> viewRbStudyApp(@PathVariable Integer currPage, @PathVariable Integer rbStudyAppId) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: viewStudyApp()");
		
		Map<String,Object> responseMap = new HashMap<String,Object>();
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		StudyDataForm studyDataForm = null;
		
				// 1. Retrieve RbStudyApplication object
				// 2. Retrieve studyDataForm obj using RbStudyApplication studyDataFormId
				RbStudyApplication rbStudyApplication = rbStudyApplicationService.fetchRbStudyApplication(rbStudyAppId);
				if(rbStudyApplication !=null) {
					rbStudyApplicationService.updateTaskStatus(rbStudyApplication,SystemConstant.TASK_STATUS_IN_PROGRESS);
					int studyDataFormId =rbStudyApplication.getStudyDataFormId() ;
					studyDataForm = studyDataFormService.fetchStudyDataForm(studyDataFormId);
				}else {
					throw new Exception("Exception from viewStudyApp() : rbStudyApplication is not present");
				}
				
				
//				studyDataForm = studyApplicationService.searchforDataIdByStudyId(studyAppId);

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
	
	
	
	@PostMapping("/viewMyRbTasksForm/saveDraftNextPage/{currPage}/studyApp/view/{rbStudyAppId}")
	public ResponseEntity<?> viewAndSaveDraftedNextPageStudyApp(@PathVariable Integer currPage, @PathVariable Integer rbStudyAppId,  @RequestBody HashMap<String, String> answerList) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: viewAndSaveDraftedNextPageStudyApp()");
		
		
		System.out.println("answeMapList = "+ answerList);
		Map<String,Object> responseMap = new HashMap<String,Object>();
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		StudyDataForm studyDataForm = null;
		
	
		
		// 1. Retrieve RbStudyApplication object
		// 2. Retrieve studyDataForm obj using RbStudyApplication studyDataFormId
		RbStudyApplication rbStudyApplication = rbStudyApplicationService.fetchRbStudyApplication(rbStudyAppId);
		if(rbStudyApplication !=null) {
			int studyDataFormId =rbStudyApplication.getStudyDataFormId() ;
			studyDataForm = studyDataFormService.fetchStudyDataForm(studyDataFormId);
		}else {
			throw new Exception("Exception from viewStudyApp() : rbStudyApplication is not present");
		}
		
		
				
//				studyDataForm = studyApplicationService.searchforDataIdByStudyId(studyAppId);

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
	
	
	@PostMapping("/viewMyRbTasksForm/endDraftPage/{currPage}/studyApp/view/{rbStudyAppId}")
	public ResponseEntity<?> viewAndendDraftPageStudyApp(@PathVariable Integer currPage, @PathVariable Integer rbStudyAppId,  @RequestBody HashMap<String, String> answerList) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: viewAndSaveDraftedNextPageStudyApp()");
		
//		Map<String,Object> responseMap = new HashMap<String,Object>();
//		
//		viewAndSaveDraftedNextPageStudyApp(currPage,studyAppId,answerList);
//		
//		studyApplicationService.callStudyDataFromServiceForLock(studyAppId);
//		
//		sendStudyAfterEndOfStudyForm(studyAppId);		
//		
//		responseMap.put("load","Successful");
//		responseMap.put("studyAppId",studyAppId);

	return ResponseEntity.ok("End Draft Form");

	}
	

	@PostMapping("/viewMyRbTasksForm/reviewOutcome/approved/{reviewerId}/{rbStudyAppId}")
	public ResponseEntity<?> DecisionOnRbAppOnApproval(@PathVariable Integer reviewerId, @PathVariable Integer rbStudyAppId) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: DecisionOnRbApp()");
		
	String outcome= SystemConstant.REVIEW_OUTCOME_APPROVED;
	String comments = "";
		rbStudyApplicationService.studyOutcome(rbStudyAppId,reviewerId, outcome, comments);
		
		
		
	return ResponseEntity.ok("DecisionOnRbApp Form");

	}
	
	
	@PostMapping("/viewMyRbTasksForm/reviewOutcome/rejected/{reviewerId}/{rbStudyAppId}")
	public ResponseEntity<?> DecisionOnRbAppOnRejection(@PathVariable Integer reviewerId, @PathVariable Integer rbStudyAppId) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: DecisionOnRbApp()");
		
		String outcome= SystemConstant.REVIEW_OUTCOME_REJECTED;
		String comments = "";
		rbStudyApplicationService.studyOutcome(rbStudyAppId,reviewerId, outcome, comments);
		
		
		
	return ResponseEntity.ok("DecisionOnRbApp Form");

	}
	
	@PostMapping("/viewMyRbTasksForm/reviewOutcome/correction/{reviewerId}/{rbStudyAppId}")
	public ResponseEntity<?> DecisionOnRbAppOnCorrection(@PathVariable Integer reviewerId, @PathVariable Integer rbStudyAppId, @RequestBody Map commentMap) throws Exception{
		System.out.println("Inside class:StudyApplicationResource method: DecisionOnRbApp()");
		
		String outcome= SystemConstant.REVIEW_OUTCOME_CORRECTIONS;
		String comments= "";
		if(commentMap.containsKey("correctionComment")) {
			comments =commentMap.get("correctionComment").toString();
		}
		rbStudyApplicationService.studyOutcome(rbStudyAppId,reviewerId, outcome, comments);
		
		
		
	return ResponseEntity.ok("DecisionOnRbApp Form");

	}
	
	
	
	
}

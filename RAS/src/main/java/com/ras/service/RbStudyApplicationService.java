package com.ras.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.RbStudyApplication;
import com.ras.model.StudyApplication;
import com.ras.model.StudyContacts;
import com.ras.repository.RbStudyApplicationRepository;
import com.ras.service.mongodbOperations.NextSequenceService;
import com.ras.util.SystemConstant;


@Service
public class RbStudyApplicationService {

	@Autowired
	RbStudyApplicationRepository repository;

	@Autowired
	NextSequenceService nextSequenceService;
	
	@Autowired
	StudyApplicationService studyApplicationService;
	
	@Autowired
	StudyContactsService studyContactsService;
	
	public void createRbStudyAppForFirstTime_PI(StudyApplication studyApp, int reviewerId, int rbId) {
		
		int componentType = SystemConstant.STUDY_COMPONENT_APPLICATION;
		int taskStatus = SystemConstant.TASK_STATUS_UNREAD;
//		int reviewerId = -1;
//		int rbId= -1;
		String status = "active";
		Date createdDate = new Date();
		Date modifiedDate  = new Date();
		String reviewerOutcome = "";
		String reviewerComment = "";
		
		
		RbStudyApplication rbStudyApp = new RbStudyApplication(studyApp.getId(),
				studyApp.getCurrentStudyDataFormId(), 
				studyApp.getStudyTitle(),
				studyApp.getOneWordIdentifier(),
				studyApp.getGender(),
				studyApp.getAge(), 
				studyApp.getNoOfHumanRequired(),
				studyApp.getTypeOfAnimal(),
				studyApp.getNoOfAnimalRequired(),
				studyApp.getFilter1(), 
				studyApp.getFilter2(), 
				studyApp.getResearcherID(), 
				studyApp.getCreatorId(), 
				rbId,
				componentType, 
				taskStatus, 
				reviewerId, 
				reviewerOutcome,
				reviewerComment,
				status, 
				createdDate, 
				modifiedDate);
		
				
//		RbStudyApplication rbStudyApp = new RbStudyApplication(studyAppId.getStudyTitle(),  
//				studyAppId.getOneWordIdentifier(), studyAppId.getGender(),  studyAppId.getAge(),
//				studyAppId.getNoOfHumanRequired(),  studyAppId.getTypeOfAnimal(),  
//				studyAppId.getNoOfAnimalRequired(), studyAppId.getFilter1(),  studyAppId.getFilter2(), 
//				studyAppId.getResearcherID(), studyAppId.getCreatorId(), -1, "");
//		rbStudyApp.setStatus("active");
//		rbStudyApp.setCreatedDate(new Date());
//		rbStudyApp.setModifiedDate(new Date());
		
		int seq = nextSequenceService.getNextSequenceForRbStudyApplicationId("customSequences");
		System.out.println("dynamicTableId generated" + seq);
		rbStudyApp.setId(seq);
		
		
		repository.save(rbStudyApp);
			
		
		
	}
	
	
// For corrections and approval	
public RbStudyApplication createRbStudyAppForNextState(RbStudyApplication currRbStudyApp, int futureReviewerId, int futureRbId , int nextStudyDataFormId) {
		
		int componentType = SystemConstant.STUDY_COMPONENT_APPLICATION;
		int taskStatus = SystemConstant.TASK_STATUS_UNREAD;
		String status = "active";
		Date createdDate = new Date();
		Date modifiedDate  = new Date();
		
		

		int seq = nextSequenceService.getNextSequenceForRbStudyApplicationId("customSequences");
		System.out.println("dynamicTableId generated" + seq);
		
		RbStudyApplication rbStudyApp = new RbStudyApplication(currRbStudyApp.getStudyAppId(),
				nextStudyDataFormId, 
				currRbStudyApp.getStudyTitle(),
				currRbStudyApp.getOneWordIdentifier(),
				currRbStudyApp.getGender(),
				currRbStudyApp.getAge(), 
				currRbStudyApp.getNoOfHumanRequired(),
				currRbStudyApp.getTypeOfAnimal(),
				currRbStudyApp.getNoOfAnimalRequired(),
				currRbStudyApp.getFilter1(), 
				currRbStudyApp.getFilter2(), 
				currRbStudyApp.getResearcherID(), 
				currRbStudyApp.getCreatorId(), 
				futureRbId,
				componentType, 
				taskStatus, 
				futureReviewerId, 
				"",
				"",
				status, 
				createdDate, 
				modifiedDate);
	
		rbStudyApp.setId(seq);
		repository.save(rbStudyApp);
			
		return rbStudyApp;
		
	}


	
	public void findAndSendRbStudyAppToNextState(int studyAppId, int CURR_STATE, int FUTURE_STATE, int destinationReviewerId, int destinationRbId , int currentRbStudyAppId, String reviewOutcome) throws Exception{
		
		StudyApplication  studyApp = studyApplicationService.findByAppId(studyAppId);
		if(studyApp !=null) {
			String reviewerOutcome =null;
			String reviewerComment = null;
			if(destinationReviewerId == 0)
				throw new Exception("destinationReviewerId id sent 0, its invalid");
			
			if(CURR_STATE == SystemConstant.STATE_DRAFT && FUTURE_STATE == SystemConstant.STATE_PI) {
				
				 destinationRbId = -1;
			
				 createRbStudyAppForFirstTime_PI(studyApp, destinationReviewerId, destinationRbId);
			}
			else if(CURR_STATE == SystemConstant.STATE_PI && FUTURE_STATE == SystemConstant.STATE_GATEKEEPER) {
				//te tulhe pahana padin
				//TODO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> connect to reviewBoard to get gatekeeper board id and assigned user
				
				RbStudyApplication currRbStudyApp = findByRbStudyAppId(currentRbStudyAppId);
				int futureReviewerId = SystemConstant.REVIEWBOARD_GATEKEEPER_USERID;
				int futureRbId 		 = SystemConstant.REVIEWBOARD_GATEKEEPER_ID;
				int nextStudyDataFormId = currRbStudyApp.getStudyDataFormId();
				
				createRbStudyAppForNextState(currRbStudyApp, futureReviewerId, futureRbId, nextStudyDataFormId);
				
				
				currRbStudyApp.setTaskStatus(SystemConstant.TASK_STATUS_COMPLETE);
				currRbStudyApp.setReviewerOutcome(SystemConstant.REVIEW_OUTCOME_APPROVED);
			
				repository.save(currRbStudyApp);
				
			}
			else if(CURR_STATE == SystemConstant.STATE_GATEKEEPER && FUTURE_STATE == SystemConstant.STATE_REVIEWBOARD) {
				//te tulhe pahana padin
				//TODO
				RbStudyApplication currRbStudyApp = findByRbStudyAppId(currentRbStudyAppId);
				int futureReviewerId = SystemConstant.REVIEWBOARD_IRB_USERID;
				int futureRbId 		 = SystemConstant.REVIEWBOARD_IRB_ID;
				int nextStudyDataFormId = currRbStudyApp.getStudyDataFormId();
				
				createRbStudyAppForNextState(currRbStudyApp, futureReviewerId, futureRbId, nextStudyDataFormId);
				
				currRbStudyApp.setTaskStatus(SystemConstant.TASK_STATUS_COMPLETE);
				currRbStudyApp.setReviewerOutcome(SystemConstant.REVIEW_OUTCOME_APPROVED);
			
				repository.save(currRbStudyApp);
				
			}
			else if(CURR_STATE == SystemConstant.STATE_REVIEWBOARD && FUTURE_STATE == SystemConstant.STATE_RETURN_TO_RESEARCHER) {
				//te tulhe pahana padin
				//TODO
				if(reviewOutcome!= null && reviewOutcome.equals(SystemConstant.REVIEW_OUTCOME_APPROVED)) {
					studyApplicationService.callStudyAppServiceForUpdate(studyAppId, SystemConstant.STUDY_APP_STATUS_APPROVED);
				}
				
			}
	
		}
		else {
			throw new Exception(" studyApp does not exist.......");
		}
			
	
	}
	
	public List<RbStudyApplication> getRbAppsByReviewer(int reviewerId){
		List<RbStudyApplication> rbStudyApplications = repository.findByReviewerId(reviewerId);
		return rbStudyApplications;
	}
	
	public RbStudyApplication findByRbStudyAppId(int id){
		RbStudyApplication rbApp= null;
		Optional<RbStudyApplication> db = repository.findById(id);
		if(db.isPresent()) {
			rbApp = db.get();
		}
		return rbApp;
	}
	
	
	public RbStudyApplication fetchRbStudyApplication(int rbStudyAppId) {
		Optional<RbStudyApplication> db= repository.findById(rbStudyAppId);
		RbStudyApplication rbStudyApplication = null;
		if(db.isPresent()) {
			rbStudyApplication = db.get();
		}
		return rbStudyApplication;
	}

	public void studyOutcome(int rbStudyAppId,int reviewerId,String outcome, String comments) throws Exception {
		//rdStudyApp obj 
		
		if(outcome !=null) {
			
			if(outcome.equals(SystemConstant.REVIEW_OUTCOME_APPROVED)) {
				studyOutcomeApprove(rbStudyAppId,reviewerId);
			}
			else if(outcome.equals(SystemConstant.REVIEW_OUTCOME_REJECTED)) {
				studyOutcomeReject(rbStudyAppId,reviewerId);
			}
			else if(outcome.equals(SystemConstant.REVIEW_OUTCOME_CORRECTIONS)) {
				studyOutcomeCorrection(rbStudyAppId,reviewerId, comments);
			}
		}else {
			throw new Exception("outcome not present");
		}
		
		
	}
	
	
	private void studyOutcomeApprove(int rbStudyAppId,int reviewerId) throws Exception {

		RbStudyApplication rbStudyApp = findByRbStudyAppId(rbStudyAppId);
		int currRBId = rbStudyApp.getRbId();
		
		int CURR_STATE = 0;
		int FUTURE_STATE = 0;
		int destinationReviewerId = -1;
		int destinationRbId  = -1;
		int currentRbStudyAppId = -1;
		
		StudyContacts PIContact = studyContactsService.getStudyContactByType(rbStudyApp.getStudyAppId(), SystemConstant.TYPE_PRINCIPAL_INVESTIGATOR);
		
		if(currRBId == -1 && PIContact.getUserId() == reviewerId) {
			
			CURR_STATE = SystemConstant.STATE_PI;
			FUTURE_STATE = SystemConstant.STATE_GATEKEEPER;
			destinationReviewerId = SystemConstant.REVIEWBOARD_GATEKEEPER_USERID;
			destinationRbId  = SystemConstant.REVIEWBOARD_GATEKEEPER_ID;
			currentRbStudyAppId = rbStudyAppId;
			
			findAndSendRbStudyAppToNextState(rbStudyApp.getStudyAppId(), CURR_STATE, FUTURE_STATE, destinationReviewerId, destinationRbId, currentRbStudyAppId, SystemConstant.REVIEW_OUTCOME_APPROVED);
			
		}
		else if(currRBId == SystemConstant.REVIEWBOARD_GATEKEEPER_ID && SystemConstant.REVIEWBOARD_GATEKEEPER_USERID == reviewerId) {
			
			CURR_STATE = SystemConstant.STATE_GATEKEEPER;
			FUTURE_STATE = SystemConstant.STATE_REVIEWBOARD;
			destinationReviewerId = SystemConstant.REVIEWBOARD_IRB_USERID;
			destinationRbId  = SystemConstant.REVIEWBOARD_IRB_ID;
			currentRbStudyAppId = rbStudyAppId;
			
			findAndSendRbStudyAppToNextState(rbStudyApp.getStudyAppId(), CURR_STATE, FUTURE_STATE, destinationReviewerId, destinationRbId, currentRbStudyAppId, SystemConstant.REVIEW_OUTCOME_APPROVED);
			
		}
		else {
			throw new Exception("Unknown Approved state....");
		}
		
		
		
	}
	
	private void studyOutcomeReject(int rbStudyAppId,int reviewerId) {
		
		//rdStudyApp obj 
		
		
	}
	
	private void studyOutcomeCorrection(int rbStudyAppId,int reviewerId, String comments) {
		
		//rdStudyApp obj 
		
		
	}
	
	
	public void updateTaskStatus(RbStudyApplication rbStudyApplication,int taskStatus) {
		if(rbStudyApplication !=null) {
			rbStudyApplication.setTaskStatus(taskStatus);
			repository.save(rbStudyApplication);
			
		}
		
	}
	
}

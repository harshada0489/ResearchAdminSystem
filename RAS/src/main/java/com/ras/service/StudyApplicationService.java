package com.ras.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.ras.model.Question;
import com.ras.model.StudyApplication;
import com.ras.model.StudyContactsConfig;
import com.ras.model.StudyDataForm;
import com.ras.model.SystemForm;
import com.ras.model.User;
import com.ras.model.payload.request.LoginRequest;
import com.ras.repository.QuestionRepository;
import com.ras.repository.StudyDataFormRepository;
import com.ras.resource.StudyApplicationRepository;
import com.ras.service.mongodbOperations.NextSequenceService;


@Service
public class StudyApplicationService {

	@Autowired
	StudyApplicationRepository studyApplicationRepository;
	
	@Autowired
	QuestionRepository qrepository;
	
	
	@Autowired
	StudyDataFormRepository studyDataFormRepository;
	
	@Autowired
	NextSequenceService nextSequenceService;
	
	@Autowired
	SystemFormService systemFormService;
	
	@Autowired
	StudyDataFormService studyDataFormService;
	
	@Autowired
	QuestionService qservice;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	StudyContactsService studyContactsService;
	
	@Autowired
	RbStudyApplicationService rbStudyApplicationService;
	
	public StudyApplicationService() {
		// TODO Auto-generated constructor stub
	}

	public int addCreateStudyDefaultValues(StudyApplication studyApp) {
		MongoCollection<Document> collection = null;
		
		HashMap<String, String> hmap = new HashMap<>();
		studyApp.setCreatedDate(new java.util.Date());
		studyApp.setModifiedDate(new java.util.Date());
		studyApp.setStatus("Draft");
		System.out.println("Calling from addCreateStudyDefaultValues()" + studyApp);
		
		
		int seq = nextSequenceService.getNextSequenceForstudyApplicationId("customSequences");
		System.out.println("studyApp Id generated" + seq);
		studyApp.setId(seq);

		studyApplicationRepository.save(studyApp);
		
		Integer studyAppId = studyApp.getId();
		System.out.println("studyApp id= " + studyAppId);
		
		return studyAppId;
		
	}
	
	public String createFilterList(String filter1, String filter2) {
		System.out.println("=================== Inside method: createFilterList() =================");
		  String filterList = "";
		  
		  List<String> list = new ArrayList<String>(); 
		  list.add(filter1);
		  list.add(filter2);
		  java.util.Collections.sort(list);
		  for(String order: list){
			  filterList = filterList.concat(order) + "_";
			}
		  filterList= filterList.substring(0, filterList.length() - 1);
		  
		  return filterList;
	  }
	
	public List<HashMap<String,String>> getStudyForm(String pageNumber ,StudyDataForm studyDataForm) {
		System.out.println("=================== Inside class: StudyApplicationService and  method: getStudyForm() =================");
		HashMap<String, String> hmap = new HashMap<>();
		
		List<HashMap<String,String>> questionList = new ArrayList<>();

		List<Question> qList = new ArrayList<>();
		
	
				hmap.put("studyAppDataId",studyDataForm.getStudyAppId()+"");

				Integer systemFormId= studyDataForm.getSystemFormId();
				Integer studyDataFormId= studyDataForm.getId();
				
				
				Integer studyAppDataId= studyDataForm.getDynamicTableDataId();
				
				qList= qrepository.findByFormIdAndPageNumber(systemFormId, pageNumber);
				System.out.println("size of qListDemo list = "+ qList);


				for(int count = 0 ; count< qList.size(); count++) {
					HashMap<String, String> qmap = new HashMap<>();
					qmap.put("studyAppDataId", studyAppDataId+"");
					qmap.put("studyDataId", studyAppDataId+"");	
					qmap.put("studyDataFormId", studyDataFormId+"");	
					
					qmap.put("studyId", studyDataForm.getStudyAppId() +"");	
					
					qmap.put("page", qList.get(count).getPageNumber());
					qmap.put("systemFormId", qList.get(count).getFormId()+"");
					qmap.put("questionNumber", qList.get(count).getQuestionNumber());
					qmap.put("questionText", qList.get(count).getQuestionText());
					qmap.put("answerType", qList.get(count).getAnswerType());
					qmap.put("dbColumnName", qList.get(count).getDbColumnName());
					
					
					questionList.add(qmap);
					
				}
			
			System.out.println("questionList" + questionList);

		return questionList;
	}
	

	
public int getTheCountOfQuestionPages(String systemFormIdString) {
		
		
		List<Question> qList = new ArrayList<>();
		
		HashSet<String> hset = new HashSet<>();
		
		Integer systemFormId = Integer.parseInt(systemFormIdString);
		
		qList= qrepository.findByFormId(systemFormId);
		System.out.println("size of qListDemo list = "+ qList);
		
		int countPage = 0;
		
		if(qList.size()>0) {
			
			for(int count = 0 ; count< qList.size(); count++) {
				hset.add(qList.get(count).getPageNumber());
			}
			if(hset.size()>0) {
				countPage = hset.size();
			}
		}
		System.out.println("countPage = "+ countPage);
		
		return countPage;
		
	}


	
	
	public void findQuestionForPage(Integer systemFormId, String page) {
		System.out.println(" Inside class CreateStudyService & method: findQuestionForPage()");
		
		List<Question> qList = new ArrayList<>();
		
		
		System.out.println("systemFormId = " + systemFormId);
		
		System.out.println("page = " + page);
		
		qList = qrepository.findByFormIdAndPageNumber(systemFormId, page);
		
		System.out.println("qList size = " + qList.size());
		System.out.println("qList to String = " + qList.toString());
		
		
	}
	
	
	public StudyDataForm searchforDataId(String studyDataFormId) {
		StudyDataForm studyDataForm = null;
		studyDataForm = studyDataFormService.getStudyDataFormObj(studyDataFormId);
		return studyDataForm;
	}
	
	
	
	public List<HashMap<String,String>> getStudyFormByPageId( String pageNumber , StudyDataForm studyDataForm) {
		System.out.println("=================== Inside method: getStudyForm() =================");
		List<HashMap<String,String>> questionList = new ArrayList<>();
		
		questionList = qservice.getQuestionList(pageNumber, studyDataForm);
		

		return questionList;
	}
	
	public HashMap<String, String> searchForfilterList(Integer studyAppId) {
		
		HashMap<String, String> hmap = new HashMap<>();
		Optional<StudyApplication> db = studyApplicationRepository.findById(studyAppId);
		if(db.isPresent()) {
			StudyApplication studyApp = db.get();
			String filter1=studyApp.getFilter1();
			String filter2 = studyApp.getFilter2();
			if(!(filter1.isEmpty()) && !(filter2.isEmpty())) {
				hmap = systemFormService.searchSystemFormOnBasisOffilterList(filter1, filter2);
				hmap.put("studyAppId" , studyAppId+"");
			}
		}
		
		return hmap;
	}
	
	public Integer putEntryInStudyDataForm(HashMap<String, String> hmap) {
		System.out.println("Inside class : StudyApplicationService and method : putEntryInStudyDataForm()");
		
		Integer systemFormDataId = studyDataFormService.updateStudyDataForm(hmap);
		return systemFormDataId;
	}
	
	public StudyDataForm getStudyDataApp(Integer systemFormDataId) {
		StudyDataForm studyDataForm = null;
		System.out.println("Inside class : StudyApplicationService and method : insertInDynamicTable()");
		studyDataForm = studyDataFormService.getStudyDataApp(systemFormDataId);
		return studyDataForm;
	}
	
	public Integer updateDynamicTable(String dynamicTableName, Integer studyDataFormId, Integer systemFormId) throws Exception {
		Integer dynamicTableDataId = null;
		System.out.println("Inside class : StudyApplicationService and method : updateDynamicTable()");
		dynamicTableDataId  = studyDataFormService.updateDynamicTable(dynamicTableName, studyDataFormId, systemFormId);
		
		return dynamicTableDataId;
	}
	
	public void updateStudyDataFormWithDynamicId(StudyDataForm studyDataForm, Integer dynamicTableDataId) {
		System.out.println("Inside class : StudyApplicationService and method : updateStudyDataFormWithDynamicId()");
		
		studyDataFormService.updateStudyDataFormWithDynamicId(studyDataForm,dynamicTableDataId);
		
		
	}
	
	public void calldynamicTableService(String dynamicTableName,Integer dynamicTableDataId, Map<String, Object> dbColumnNamesAnswerList) {
		System.out.println("Inside class : StudyApplicationService and method : calldynamicTableService()");
		MongoListCollections.insertdbColumnNamesInDynamicTable(dynamicTableName, dynamicTableDataId, dbColumnNamesAnswerList);
		
	}
	
	
	private static List<StudyApplication> forms = new ArrayList<>();
	
	public List<StudyApplication> getAllStudyApp(Integer creatorId){
		System.out.println("Inside class: SystemFormService and method: getAllDBForms() ");
		List<StudyApplication> dbAllForms = studyApplicationRepository.findByCreatorId(creatorId);
		return dbAllForms;
	}
	
	public List<User> callForUserService() {
		List<User> uList = userService.getUserList();
		return uList;
	}
	
	public List<StudyContactsConfig> callForStudyContactsConfigService() {
		List<StudyContactsConfig> tList = studyContactsService.getUserTypes();
		return tList;
	}
	
	public void callRbService(Integer studyAppId){
		Optional<StudyApplication> db = studyApplicationRepository.findById(studyAppId);
		if(db.isPresent()) {
			StudyApplication  studyApp = db.get();
			rbStudyApplicationService.addRvStudyApp(studyApp);
		}
		
	}
}

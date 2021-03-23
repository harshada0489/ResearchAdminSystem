package com.ras.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.ras.model.Question;
import com.ras.model.StudyApplication;
import com.ras.model.StudyDataForm;
import com.ras.model.SystemForm;
import com.ras.model.payload.request.LoginRequest;
import com.ras.repository.QuestionRepository;
import com.ras.repository.StudyDataFormRepository;
import com.ras.resource.StudyApplicationRepository;
//import com.ras.resource.LoginRequestRepository;

@Service
public class StudyApplicationService {

	@Autowired
	StudyApplicationRepository studyApplicationRepository;
	
	@Autowired
	QuestionRepository qrepository;
	
	
	@Autowired
	StudyDataFormRepository studyDataFormRepository;
	
	
	
	public StudyApplicationService() {
		// TODO Auto-generated constructor stub
	}

	public String addCreateStudyDefaultValues(StudyApplication createStudy) {
		MongoCollection<Document> collection = null;
		
		HashMap<String, String> hmap = new HashMap<>();
		createStudy.setCreatedDate(new java.util.Date());
		createStudy.setModifiedDate(new java.util.Date());
		createStudy.setStatus("pending");
		System.out.println("Calling from addCreateStudyDefaultValues()" + createStudy);
		
		
		studyApplicationRepository.save(createStudy);
		
		return "success";
		
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
	
	public HashMap<String, String> getStudyForm(String studyTitle, String oneWordIdentifier) {
		System.out.println("=================== Inside method: getStudyForm() =================");
		HashMap<String, String> hmap = new HashMap<>();
//		List<Document> questionList = new ArrayList<>();
		String returnVal="";
		System.out.println("studyTitle=" + studyTitle + " oneWordIdentifier = " + oneWordIdentifier);
		
		Optional<StudyApplication> db = studyApplicationRepository.findByStudyTitle(studyTitle);
		
		System.out.println("db.isPresent() =" + db.isPresent());

		if(db.isPresent()) {
			StudyApplication study = db.get();
	  		
	  		String filter1 = study.getFilter1();
			String filter2 =  study.getFilter2();
			
			String studyId = study.getId();
			
			String filterCombo = createFilterList(filter1, filter2);
			System.out.println("filterCombo = "  + filterCombo);
			
			hmap= MongoListCollections.getSystemFormByFilters(filterCombo);
			
			if(hmap.containsKey("systemFormId")) {
				
				
				
				String systemFormId  = hmap.get("systemFormId");
				
				String filterFormId  = hmap.get("filterFormId");
				
				
				StudyDataForm studyMapForm = new StudyDataForm(studyId,systemFormId,filterFormId);

				studyMapForm.setCreatedDate(new Date());
				studyMapForm.setModifiedDate(new Date());
				studyMapForm.setStatus("active");
				
				studyDataFormRepository.save(studyMapForm);

			}
			
			
			hmap.put("studyId", studyId);
			hmap.put("page", "1");
			
			if(hmap.containsKey("systemFormId")) {
				String systemFormId= hmap.get("systemFormId");
				
				String pageNumber= hmap.get("page");
				
//				questionList = MongoListCollections.getQuestionSet(systemFormId,pageNumber);
//				System.out.println("size of question list = "+ questionList.size() );
				
			}
			
			
			
			
			System.out.println("hmap = " + hmap);
		}

		return hmap;
	}
	
	
	
	public void findQuestionForPage(String systemFormId, String page) {
		System.out.println(" Inside class CreateStudyService & method: findQuestionForPage()");
		
		List<Question> qList = new ArrayList<>();
		
		
		System.out.println("systemFormId = " + systemFormId);
		
		System.out.println("page = " + page);
		
		qList = qrepository.findByFormIdAndPageNumber(systemFormId, page);
		
		System.out.println("qList size = " + qList.size());
		System.out.println("qList to String = " + qList.toString());
		
		
	}
	
}

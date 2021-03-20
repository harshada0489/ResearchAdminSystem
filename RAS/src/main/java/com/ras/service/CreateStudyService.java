package com.ras.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.ras.model.CreateStudy;
import com.ras.model.Question;
import com.ras.model.SystemForm;
import com.ras.model.payload.request.LoginRequest;
import com.ras.repository.QuestionRepository;
import com.ras.resource.CreateStudyRepository;
//import com.ras.resource.LoginRequestRepository;

@Service
public class CreateStudyService {

	@Autowired
	CreateStudyRepository createStudyRepository;
	
//	@Autowired
//	QuestionRepository repository;
	
	public CreateStudyService() {
		// TODO Auto-generated constructor stub
	}

	public String addCreateStudyDefaultValues(CreateStudy createStudy) {
		MongoCollection<Document> collection = null;
		
		HashMap<String, String> hmap = new HashMap<>();
		createStudy.setCreatedDate(new java.util.Date());
		createStudy.setModifiedDate(new java.util.Date());
		createStudy.setStatus("pending");
		System.out.println("Calling from addCreateStudyDefaultValues()" + createStudy);
		
		
		createStudyRepository.save(createStudy);
		
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
		
		Optional<CreateStudy> db = createStudyRepository.findByStudyTitle(studyTitle);
		
		System.out.println("db.isPresent() =" + db.isPresent());

		if(db.isPresent()) {
			CreateStudy study = db.get();
	  		
	  		String filter1 = study.getFilter1();
			String filter2 =  study.getFilter2();
			
			String studyId = study.getId();
			
			String filterCombo = createFilterList(filter1, filter2);
			System.out.println("filterCombo = "  + filterCombo);
			
			hmap= MongoListCollections.getSystemFormByFilters(filterCombo);
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
	
}

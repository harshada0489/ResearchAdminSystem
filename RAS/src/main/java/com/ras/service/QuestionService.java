package com.ras.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ras.model.Question;
import com.ras.model.StudyDataForm;
import com.ras.repository.QuestionRepository;
import com.ras.service.mongodbOperations.NextSequenceService;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository repository;
	
	@Autowired
	NextSequenceService nextSequenceService;

	
	public QuestionService() {
		// TODO Auto-generated constructor stub
	}

	public String addQuestionDetails(List<Question> question) {

		System.out.println("Calling from class: QuestionService & method: addQuestionDetails() ");
			for(int counter=0; counter<question.size(); counter++) {
				Question q=question.get(counter);
				q.setStatus("active");
				q.setCreatedDate(new java.util.Date());
				q.setModifiedDate(new java.util.Date());

				int seq = nextSequenceService.getNextSequenceForQuestionId("customSequences");
				System.out.println("dynamicTableId generated" + seq);
				q.setId(seq);

				
				
				repository.save(q);
			}
		
		return "Successful";
	}
	
	
	public List<HashMap<String,String>> getQuestionList( String pageNumber , StudyDataForm studyDataForm) {
		
		
		HashMap<String, String> hmap = new HashMap<>();
		List<HashMap<String,String>> questionList = new ArrayList<>();

		List<Question> qList = new ArrayList<>();
		
		Integer systemFormId= studyDataForm.getSystemFormId();
		
		qList = repository.findByFormIdAndPageNumber(systemFormId, pageNumber);
		

				System.out.println("size of qListDemo list = "+ qList);			
				

				Integer studyDataFormId= studyDataForm.getId();

				
				Integer studyAppDataId= studyDataForm.getDynamicTableDataId();

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
	
}

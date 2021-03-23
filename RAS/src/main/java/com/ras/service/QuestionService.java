package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ras.model.Question;
import com.ras.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository repository;
	
	public QuestionService() {
		// TODO Auto-generated constructor stub
	}

	public String addQuestionDetails(List<Question> question) {
		
//		this.formId = formId;
//		this.pageNumber = pageNumber;
//		this.questionNum = questionNum;
//		this.questionText = questionText;
//		this.answerType = answerType;
//		this.dbColumnName = dbColumnName;
		
		System.out.println("Calling from class: QuestionService & method: addQuestionDetails() ");
			for(int counter=0; counter<question.size(); counter++) {
				Question q=question.get(counter);
				q.setStatus("active");
				q.setCreatedDate(new java.util.Date());
				q.setModifiedDate(new java.util.Date());

				repository.save(q);
			}
		
		return "Successful";
	}
	
}

package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.Page;
import com.ras.model.Question;
import com.ras.repository.PageRepository;
import com.ras.repository.QuestionRepository;
import com.ras.service.mongodbOperations.NextSequenceService;

@Service
public class PageService {

	@Autowired
	PageRepository repository;
	
	@Autowired
	NextSequenceService nextSequenceService;
	
	
	public String pageOneCreation(int pageNumber, int formId) {
		System.out.println("Calling from class: PageService & method: pageOneCreation() ");
		System.out.println("pageNumber = " + pageNumber + "  formId = " + formId);
				Page page = new Page(pageNumber, formId);
				
				int seq = nextSequenceService.getNextSequenceForPageId("customSequences");
				  
				  System.out.println("seq generated = " + seq);
				  page.setId(seq);
				  
				page.setStatus("active");
				page.setCreatedDate(new java.util.Date());
				page.setModifiedDate(new java.util.Date());

				repository.save(page);
			
		
		return "Successful";
	}
	
	public int findPageId(int formId, int pageNumber) {
		System.out.println("Calling from class: PageService & method: findPageId() ");
		Integer pageId = 0;
		Page page = repository.findByFormIdAndPageNumber(formId, pageNumber);
		if(page != null) {
			pageId = page.getId();
			System.out.println("pageId = " + pageId);
		}
	return pageId;
	}

}

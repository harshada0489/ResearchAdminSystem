package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.Page;
import com.ras.model.Question;
import com.ras.repository.PageRepository;
import com.ras.repository.QuestionRepository;

@Service
public class PageService {

	@Autowired
	PageRepository repository;
	
	public String pageOneCreation(int pageNumber, String formId) {
		System.out.println("Calling from class: PageService & method: pageOneCreation() ");
		System.out.println("pageNumber = " + pageNumber + "  formId = " + formId);
				Page page = new Page(pageNumber, formId);
				page.setStatus("active");
				page.setCreatedDate(new java.util.Date());
				page.setModifiedDate(new java.util.Date());

				repository.save(page);
			
		
		return "Successful";
	}
	
	public String findPageId(String formId, int pageNumber) {
		System.out.println("Calling from class: PageService & method: findPageId() ");
		String pageId = "";
		Page page = repository.findByFormIdAndPageNumber(formId, pageNumber);
		if(page != null) {
			pageId = page.getId();
			System.out.println("pageId = " + pageId);
		}
	return pageId;
	}

}

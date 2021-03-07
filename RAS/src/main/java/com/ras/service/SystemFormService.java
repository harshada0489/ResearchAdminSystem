package com.ras.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.SystemForm;
import com.ras.repository.SystemFormRepository;

@Service
public class SystemFormService {

	@Autowired
	SystemFormRepository repository;
	
	private static List<SystemForm> forms = new ArrayList<>();
	
	public List<SystemForm> getAllDBForms(){
		System.out.println("Inside class: SystemFormService and method: getAllDBForms() ");
		List<SystemForm> dbAllForms = repository.findByStatus("active");
		return dbAllForms;
	}
	
	  public Optional<SystemForm> deleteById(String id) {
		  Optional<SystemForm> form = repository.findById(id);
		  
		  if(form.isPresent()) {
			  SystemForm dbForm = form.get();
			  dbForm.setStatus("inactive");
			  repository.save(dbForm);
			} else {
			    return null;
			}
		  
		  
		  
//		  System.out.println("form = " + form.));
//		  System.out.println(form.toString());
//		    if (form == null)
//		      return null;
//
//		    else {
//		    	form.toString()
//		    	repository.deleteById(id);
		    	return form;
//		    }
			    
		    
		  }

	  public Optional<SystemForm> findById(String id) {
		  Optional<SystemForm> form = repository.findById(id); 
		  
		    if (form.isPresent()) {
		      return form;
		    }
		  
		  return null;
		}
	  
	  public String addNewSystemForm(SystemForm form) {
		  System.out.println("Inside class : SystemFormService  and method : addNewSystemForm()");
		  Optional<SystemForm> dbForm = repository.findByFormName(form.getFormName());
		  System.out.println("dbForm = "+ dbForm.isPresent());
		  if(!(dbForm.isPresent())) {
			  
			  form.setStatus("active");
			  form.setCreatedDate(new java.util.Date());
			  form.setModifiedDate(new java.util.Date());
			  
			  repository.save(form);
			  System.out.println("Saved Successfully  ");
			  return "Successfully Inserted";
		  }
		  else {
			  System.out.println("Already Exist");
			  return "Form Name Already Exist";
		  }
	  }
	  
	  public String searchByFormName(String formName) {
		  System.out.println("Inside class : SystemFormService  and method : searchByFormName()" );
		  System.out.println("formName = " + formName );
		  Optional<SystemForm> db = repository.findByFormName(formName);
		  String formId = "";
		  	if(db.isPresent()) {
		  		SystemForm dbForm = db.get();
		  		formId = dbForm.getId();
		  		System.out.println("formId = " + formId );
		  	}
		  		
		  return formId;
	  }

}

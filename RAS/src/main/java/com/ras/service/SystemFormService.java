package com.ras.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ras.model.FormDetails;
import com.ras.model.SystemForm;
import com.ras.repository.SystemFormRepository;
import com.ras.service.mongodbOperations.NextSequenceService;

import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Collections;

@Service
public class SystemFormService {

	@Autowired
	SystemFormRepository repository;
	
	@Autowired
	NextSequenceService nextSequenceService;
	
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
	  
	  public String addNewSystemForm(FormDetails form) {
		  System.out.println("Inside class : SystemFormService  and method : addNewSystemForm()");
		  Optional<SystemForm> dbForm = repository.findByFormName(form.getFormName());
		  System.out.println("dbForm = "+ dbForm.isPresent());
		  if(!(dbForm.isPresent())) {
			  SystemForm sysForm = new SystemForm(form.getFormName(), form.getFormDescription());			  
			  
			  int seq = nextSequenceService.getNextSequenceForSystemFormId("customSequences");
			  
			  System.out.println("seq generated = " + seq);
			  sysForm.setId(seq);
			  String filter1 = form.getFilter1();
			  String filter2 = form.getFilter2();
			  
			  String filterCombo = createFilterList(filter1, filter2);
			 
			  
			  sysForm.setFilterList(filterCombo);

			  sysForm.setStatus("active");
			  sysForm.setCreatedDate(new java.util.Date());
			  sysForm.setModifiedDate(new java.util.Date());
			  
			  repository.save(sysForm);
			  System.out.println("Saved Successfully  ");
			  return "Successfully Inserted";
		  }
		  else {
			  System.out.println("Already Exist");
			  return "Form Name Already Exist";
		  }
	  }
	  
	  
	  public String createFilterList(String filter1, String filter2) {
		  String filterList = "";
		  
		  List<String> list = new ArrayList<String>(); 
		  list.add(filter1);
		  list.add(filter2);
		  java.util.Collections.sort(list);
		  for(String order: list){
			  filterList = filterList.concat(order) + ",";
			}
		  filterList= filterList.substring(0, filterList.length() - 1);
		  
		  return filterList;
	  }
	  public Integer searchByFormName(String formName) {
		  System.out.println("Inside class : SystemFormService  and method : searchByFormName()" );
		  System.out.println("formName = " + formName );
		  Optional<SystemForm> db = repository.findByFormName(formName);
		  Integer formId = 0;
		  	if(db.isPresent()) {
		  		SystemForm dbForm = db.get();
		  		formId = dbForm.getId();
		  		System.out.println("formId = " + formId );
		  	}
		  		
		  return formId;
	  }

	  
	  public void updatedynamicTableNameInSystemFormTable(Integer formId,String dynamicTableName) {
		  System.out.println("Inside class : SystemFormService  and method : updatedynamicTableNameInSystemFormTable()" );
		  Optional<SystemForm> db = repository.findById(formId);
		  if(db.isPresent()) {
		  		SystemForm dbForm = db.get();
		  		dbForm.setDynamicTableName(dynamicTableName);
		  		repository.save(dbForm);
		  		System.out.println("formId = " + formId );
		  	}
		  
	  }
	  
	  public HashMap<String, String> searchSystemFormOnBasisOffilterList(String filter1, String filter2) {
		  System.out.println("Inside class : SystemFormService  and method : searchSystemFormOnBasisOffilterList()" );
		  
		  HashMap<String, String> hmap = new HashMap<>();
		  String filterList= createFilterList(filter1,filter2);
		  System.out.println("filterList = " + filterList);
		  
		  int version = 1;
		  String dynamicTableName ="";
		  List<SystemForm> sysFormList = repository.findByFilterListOrderByIdDesc(filterList);
		   
		  
		  if(sysFormList.size()>0) {
			  System.out.println("sysFormList size = "+ sysFormList.size() );
			   dynamicTableName = sysFormList.get(0).getDynamicTableName();
			   version = sysFormList.get(0).getVersion();
			   int systemFormId = sysFormList.get(0).getId();
				 System.out.println("dynamicTableName=" + dynamicTableName);
				 System.out.println("version=" + version+"");
				 hmap.put("dynamicTableName", dynamicTableName);
				 hmap.put("version", version+"");
				 hmap.put("systemFormId", systemFormId+"");
		  }
		  

		 
		  return hmap;
	  }
	  

	  
}

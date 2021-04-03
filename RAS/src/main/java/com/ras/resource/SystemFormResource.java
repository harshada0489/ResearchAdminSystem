package com.ras.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.FormDetails;
import com.ras.model.SystemForm;
import com.ras.model.payload.response.MessageResponse;
import com.ras.repository.SystemFormRepository;
import com.ras.service.PageService;
import com.ras.service.SystemFormService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class SystemFormResource {

	
	@Autowired
	SystemFormService service; 
	
	@Autowired
	PageService pageService;
	
	
	
	@GetMapping("/systemForm")
	public List<SystemForm> getAllSystemForm() {
		System.out.println("Inside class: SystemFormResource and method: getAllSystemForm() ");
		List<SystemForm> allForms = service.getAllDBForms();
		System.out.println("allForms =  " + allForms);
		return allForms ;
	}
	
	
	  @DeleteMapping("/systemForm/{id}")
	  public ResponseEntity<Void> deleteForm(@PathVariable String id) {

		  System.out.println("id = " + id);
	    Optional<SystemForm> form = (Optional<SystemForm>) service.deleteById(id);

	    if (form != null) {
	      return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.notFound().build();
	  }
	 
	  
	  @GetMapping("/systemForm/edit/{id}")
	  public String editFormDetails(@PathVariable String id){
		  // SystemForm edit page 
		  System.out.println("Inside class: SystemFormResource and method: editFormDetails() , id = " + id);
			
		  
		  
			return "Successful" ;
	  }
	  
	  @GetMapping("/systemForm/view/{id}")
	  public String viewFormDetails(@PathVariable String id){
		  System.out.println("Inside class: SystemFormResource and method: viewFormDetails() , id = " + id);
			
			return "Successful" ;
	  }
	  
	  
	  @PostMapping("/systemForm/create")
	  public Map<String, String> createForm(@RequestBody FormDetails formDetails){
		  
		  HashMap <String, String> map = new HashMap<String, String>();
		  
		  // change table name SystemForm to StudyFormConnector Table
		  // add columns (1. filter => comma separated typeOfStudy and FieldOfStudy, 
		  //			  2. status => active)
		  //insert new row in StudyFormConnector Table
		  
		  System.out.println("Inside class: SystemFormResource and method: createForm() = " + formDetails.toString());
		  
			String message = service.addNewSystemForm(formDetails);
			
			Integer formId = 0;
			int pageNumber = 1; 
			Integer pageId =0;
			if(message.equals("Successfully Inserted")) {
				
				String formname = formDetails.getFormName();
				if(!(formname.isEmpty())) {
					 formId = service.searchByFormName(formname);
					if(formId != 0) {
						String pageCreation = pageService.pageOneCreation(1,formId);
						if(pageCreation.equals("Successful")) {
							pageId = pageService.findPageId(formId, pageNumber);
							if(pageId != 0) {
								map.put("formId", formId.toString());
								map.put("pageId", pageId.toString());
								map.put("pageNumber", pageNumber+"");
								return map;
							}
							
							
//							return ResponseEntity.ok(new MessageResponse(formId + "," + pageNumber));
							
						}
					}
					
				}
			
				
			}
			//return newly created SystemForm Id and Page number
			return map;
			 
	  }
	  
	  
	
//	--------------------------------------------------------------------------------------------------------------------------
	  
	 
//	  end Points
//	  1. Create Page( ip - form Id, Questions in json)
//	  
//		   - Insert Entry in page table
//		   = Insert Questions received in i/p in Question Table( using id generated for page above)
//		   
//	  2. Update Page(ip- formId, pageNumber, Questions in Json )
//	  		= for each question : 
//	  				if QuestionId present for a question then update
//	  				else ie. Quesion id not present then insert the question
//	  
	  

}

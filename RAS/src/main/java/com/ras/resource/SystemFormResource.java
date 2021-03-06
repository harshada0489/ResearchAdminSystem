package com.ras.resource;

import java.util.List;
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

import com.ras.model.SystemForm;
import com.ras.model.payload.response.MessageResponse;
import com.ras.repository.SystemFormRepository;
import com.ras.service.SystemFormService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class SystemFormResource {

	@Autowired
	SystemFormService service; 
	
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
		  System.out.println("Inside class: SystemFormResource and method: editFormDetails() , id = " + id);
			
			return "Successful" ;
	  }
	  
	  @GetMapping("/systemForm/view/{id}")
	  public String viewFormDetails(@PathVariable String id){
		  System.out.println("Inside class: SystemFormResource and method: viewFormDetails() , id = " + id);
			
			return "Successful" ;
	  }
	  
	  
	  @PostMapping("/systemForm/create")
	  public ResponseEntity<MessageResponse> createForm(@RequestBody SystemForm systemform){
		  // change table name SystemForm to StudyFormConnector Table
		  // add columns (1. filter => comma separated typeOfStudy and FieldOfStudy, 
		  //			  2. status => active)
		  //insert new row in StudyFormConnector Table
		  
		  System.out.println("Inside class: SystemFormResource and method: createForm() = " + systemform.toString());
			String message = service.addNewSystemForm(systemform);
			return ResponseEntity.ok(new MessageResponse(message));
	  }
	
//	--------------------------------------------------------------------------------------------------------------------------

}

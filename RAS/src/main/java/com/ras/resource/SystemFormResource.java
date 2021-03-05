package com.ras.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.SystemForm;
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

}

package com.ras.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ras.service.RbStudyApplicationService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class RbStudyApplicationResource {

	@Autowired
	private RbStudyApplicationService rbStudyApplicationService;
	
	
	
	
	
}

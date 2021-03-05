package com.ras.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ras.model.payload.response.JwtResponse;

import com.ras.model.LabelDetails;
import com.ras.model.payload.request.LoginRequest;
import com.ras.model.payload.response.JwtResponse;
import com.ras.repository.LabelDetailsRepository;
import com.ras.repository.UserRepository;
import com.ras.service.LabelDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ras")
public class LabelDetailsResource {

//	@Autowired
//	AuthenticationManager authenticationManager;

//	@Autowired
//	LabelDetailsRepository labelDetailsRepository ;
	
	@Autowired
	LabelDetailsService labelDetailsService;
	
	@PostMapping("/labelDetails")
	public ResponseEntity<?> addLabelDetails(@RequestBody List<LabelDetails> labelDetails ) {
		System.out.println("Inside class: LabelDetailsResource , && method: addLabelDetails()");
		System.out.println("labelDetails ====== " + labelDetails.toString());
		
		labelDetailsService.addLabelDetailsWithDefaultValues(labelDetails);
		
		return ResponseEntity.ok("success");
		
	}

}

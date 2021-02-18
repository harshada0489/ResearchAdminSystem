package com.ras.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ras.model.LoginRequest;
import com.ras.model.Research;
import com.ras.service.LoginRequestService;

@CrossOrigin(origins = {"http://localhost:3001"})
@RequestMapping("/ras")
@RestController
public class LoginRequestResource {
	
	@Autowired
	private LoginRequestRepository loginRequestRepository;
	
//	@RequestMapping(value = "ras/loginRequest?email={email}&pwd={pwd}", method=RequestMethod.GET)
	@GetMapping(path="/loginRequest")
	@ResponseBody
	public String getResearchers(@RequestParam String email, String pwd) {	
		System.out.println("String email = " + email + " String pwd = " + pwd);
		LoginRequestService loginRequestService =new LoginRequestService() ;
		String returnValue = loginRequestService.authenticateUserNameAndPwd(email, pwd , loginRequestRepository);
		
		
	return returnValue;
	
	}
	
	@RequestMapping(path="/ras/loginRequest/{id}")
	public Optional<LoginRequest> findLogin(@PathVariable("id") int id) {
	return loginRequestRepository.findById(id);

	}
	
	@PostMapping(path="/loginRequest")
	public String addLoginRequest(@RequestBody LoginRequest loginRequest) {
		LoginRequestService loginRequestService =new LoginRequestService() ;
		String returnValue = loginRequestService.addDefaultValues(loginRequest, loginRequestRepository);
		
	return returnValue;
	}
	
	@DeleteMapping(path="ras/loginRequest/{id}")
	public String deleteRequest(@PathVariable("id") int id) {
		loginRequestRepository.deleteById(id);
	return "Deleted";
	}
	
	@PutMapping(path="ras/loginRequest")
	public LoginRequest saveOrUpdateRequest(@RequestBody LoginRequest loginRequest) {
		loginRequestRepository.save(loginRequest);
	return loginRequest;
	}
	
	

}

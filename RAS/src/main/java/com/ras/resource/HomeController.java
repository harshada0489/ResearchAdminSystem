//package com.ras.resource;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ras.model.LoginRequest;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("ras/")
//public class HomeController {
//
////	@GetMapping(value="loginRequest")
////	public String homePage() {
////		return "SignUpForm";
////	}
//	@Autowired
//	LoginRequestRepository loginRequestRepository;
//	
//	@GetMapping(value="loginRequest")
//	public List<LoginRequest> getRequest(){
//		return this.loginRequestRepository.findAll();
//	}
//
//}

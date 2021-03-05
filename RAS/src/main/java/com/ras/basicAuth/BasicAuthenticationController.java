//package com.ras.basicAuth;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
//@RestController
//public class BasicAuthenticationController {
//
//	@GetMapping(path = "/basicauth")
//    public AuthenticationBean authenticate() {
//        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
//		 AuthenticationBean authenticationBean= new AuthenticationBean();
//		 authenticationBean.setMessage("You are authenticated");
//        return authenticationBean;
//    }    
//}
//package com.ras.resource;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.http.HttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.ras.model.RasSessionAttributes;
//import com.ras.model.Research;
//import com.ras.model.UserAccount;
//import com.ras.model.payload.request.LoginRequest;
//import com.ras.service.HttpSessionService;
//import com.ras.service.LoginRequestService;
//
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
//@RequestMapping("/ras")
//@RestController
//
//public class LoginRequestResource {
//	
//	
//	@Autowired
//	private LoginRequestRepository loginRequestRepository;
//	
//	@PostMapping(path="/loginRequest")
//	public String addLoginRequest(@RequestBody LoginRequest loginRequest) {
//		System.out.println("In class : LoginRequestResource & method : addLoginRequest() ");
//		LoginRequestService loginRequestService =new LoginRequestService() ;
//		String returnValue = loginRequestService.addDefaultValues(loginRequest, loginRequestRepository);
//		
//	return returnValue;
//	}	
//	
//	@RequestMapping(value = "/loginAuth", method=RequestMethod.POST)
////	@PostMapping(path="/loginAuth")
//	public Map<String, String> loginAuth(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//		System.out.println("String email = " + loginRequest.getEmailId() + " String pwd = " + loginRequest.getPwd());
//		LoginRequestService loginRequestService =new LoginRequestService() ;
//		Map<String, String> loginValue = loginRequestService.loginAuthAndAddDefaultValues(loginRequest, loginRequestRepository);
//	
//		return loginValue;
//
//	}
//	
//	
//	@RequestMapping(value = "/loginAuth/setValues", method=RequestMethod.GET)
////	@GetMapping(path="/loginAuth/setValues")
////	@ResponseBody
//	public String getSessionValues() {	
//		System.out.println("Inside class: LoginRequestResource & method: getSessionValues()");
//
//	return "";
//	
//	}
//	
//	@RequestMapping(path="/ras/loginRequest/{id}")
//	public Optional<LoginRequest> findLogin(@PathVariable("id") int id) {
//	return loginRequestRepository.findById(id);
//
//	}
//	
//	@RequestMapping(path="/ras/userAccess/")
//	public Map<String, String> userAccessDetails(HttpServletRequest request) {
//		HttpSessionService hss = new HttpSessionService();
//		HttpSession session = request.getSession(true);
//		
//		Map<String,String> navBarIcons = new HashMap<String,String>();
//		navBarIcons= hss.setNavbarIcons(request);
//		session.setAttribute("navbarIconsMap",navBarIcons);
//
//		return navBarIcons;
//
//	}
//	
//
//	
//	@DeleteMapping(path="ras/loginRequest/{id}")
//	public String deleteRequest(@PathVariable("id") int id) {
//		loginRequestRepository.deleteById(id);
//	return "Deleted";
//	}
//	
//	@PutMapping(path="ras/loginRequest")
//	public LoginRequest saveOrUpdateRequest(@RequestBody LoginRequest loginRequest) {
//		loginRequestRepository.save(loginRequest);
//	return loginRequest;
//	}
//	
//	
//
//}

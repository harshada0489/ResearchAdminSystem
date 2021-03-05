//package com.ras.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//
//import com.ras.model.payload.request.LoginRequest;
//import com.ras.resource.LoginRequestRepository;
//
//@Component
//@Controller
//
//@Service
//public class LoginRequestService {
//
//	
//	public LoginRequestService() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	public String addDefaultValues(LoginRequest loginRequest, LoginRequestRepository loginRequestRepository) {
//		loginRequest.setCreatedDate(new java.util.Date());
//		loginRequest.setModifiedDate(new java.util.Date());
//		loginRequest.setStatus("pending");
//		System.out.println("Calling from addLoginRequest()" + loginRequest);
//		loginRequestRepository.save(loginRequest);
//		return "Successful";
//	}
//	
//	public Map<String, String> loginAuthAndAddDefaultValues(LoginRequest loginRequest, LoginRequestRepository loginRequestRepository) {
//		
//		Map<String,String> returnList = new HashMap<String,String>();
//		
//		System.out.println("Before db request : Inside method ------ LoginRequestService.authenticateUserNameAndPwd()");
//		String email = loginRequest.getEmailId();
//		String pwd = loginRequest.getPwd();
//		List<LoginRequest> dbLoginRequestList = loginRequestRepository.findByEmailId(email);
//		
//		System.out.println("After db request : Inside method ------ LoginRequestService.authenticateUserNameAndPwd()");
//		
//		System.out.println("Json values : " + " EmailId = " + email + " & Pwd =" + pwd);
//			
//		if(dbLoginRequestList.size()> 0) {
//			System.out.println("Inside if condition of database list========= " + (! (dbLoginRequestList.get(0).getEmailId().equals(email)))  + " " + (! (dbLoginRequestList.get(0).getPwd().contentEquals(pwd)))  );
//			if(dbLoginRequestList.get(0).getEmailId().equals(email) &&
//					(dbLoginRequestList.get(0).getPwd().equals(pwd))) {
//				
//				returnList.put("UserId",dbLoginRequestList.get(0).getId());
//				returnList.put("firstName",dbLoginRequestList.get(0).getFirstName());
//				returnList.put("loginType",dbLoginRequestList.get(0).getLoginType());
//				
//				returnList.put("responseMessage","Successful");
//				
//				System.out.println("Successful");
//				return returnList;
//				
//				
//			}
//		}else {
//			System.out.println("Failure");
//			return returnList;
//		}
//		System.out.println("Failure");
//		return returnList;
//		
//	}
//
//}

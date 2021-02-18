package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ras.model.LoginRequest;
import com.ras.resource.LoginRequestRepository;

public class LoginRequestService {

	
	public LoginRequestService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String addDefaultValues(LoginRequest loginRequest, LoginRequestRepository loginRequestRepository) {
		loginRequest.setCreatedDate(new java.util.Date());
		loginRequest.setModifiedDate(new java.util.Date());
		loginRequest.setStatus("pending");
		System.out.println("Calling from addLoginRequest()" + loginRequest);
		loginRequestRepository.save(loginRequest);
		return "Successful";
	}
	
	public String authenticateUserNameAndPwd(String email,String pwd, LoginRequestRepository loginRequestRepository) {

		System.out.println("Before db request : Inside method ------ LoginRequestService.authenticateUserNameAndPwd()");
		List<LoginRequest> dbLoginRequestList = loginRequestRepository.findByEmailId(email);
//		LoginRequest dbLoginRequest = (LoginRequest) loginRequestRepository.findByEmailId(email);
		
		System.out.println("After db request : Inside method ------ LoginRequestService.authenticateUserNameAndPwd()");
		
		System.out.println("Json values : " + " EmailId = " + email + " & Pwd =" + pwd);
		
//		for (LoginRequest val : dbLoginRequestList) {
//			System.out.println("dbLoginRequest values : " + val);
//		}
		
//		System.out.println("dbLoginRequest values : " + dbLoginRequestList);
		
//		System.out.println("Database values : " + " EmailId = " + dbLoginRequestList.get(0).getEmailId() + " & Pwd =" + dbLoginRequestList.get(0).getPwd());
//		
		if(dbLoginRequestList.size()> 0) {
			System.out.println("Inside if condition of database list========= " + (! (dbLoginRequestList.get(0).getEmailId().equals(email)))  + " " + (! (dbLoginRequestList.get(0).getPwd().contentEquals(pwd)))  );
			if(dbLoginRequestList.get(0).getEmailId().equals(email) &&
					(dbLoginRequestList.get(0).getPwd().equals(pwd))) {
				
				System.out.println("Successful");
				return "Successful";
				
				
			}
		}else {
			System.out.println("Failure");
			return "Failure";
		}
		System.out.println("Failure");
		return "Failure";
		
	}

}

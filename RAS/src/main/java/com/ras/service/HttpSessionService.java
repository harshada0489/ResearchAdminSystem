//package com.ras.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.ras.model.RasSessionAttributes;
//import com.ras.resource.LoginRequestResource;
//
//@Component
//@Controller
//@SessionAttributes("loginParamsMap")
//@Service
//public class HttpSessionService {
//
//	
//	public HttpSessionService() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Map<String, String> setNavbarIcons(HttpServletRequest request){
//		Map<String, String> navBarIcon = new HashMap<String,String>();
//		System.out.println("Inside class: HttpSessionService & method : setNavbarIcons() ");
//		return navBarIcon;
//	}
//	
//	public Map<String, String> setModuleAccess(HttpServletRequest request){
//		Map<String, String> moduleAccess = new HashMap<String,String>();
//		System.out.println("Inside class: HttpSessionService & method : setModuleAccess() ");
//		return moduleAccess;
//	}
//	
//	public String setLoginParams(HttpServletRequest request,Map<String, String> loginParamsMap){
//		
//		System.out.println("Inside class: HttpSessionService & method : setLoginParams() ");
//					
//			HttpSession session = request.getSession(true);
//			session.setMaxInactiveInterval(ConfigConstant.SESSION_MAX_TIME_SECONDS);
//			session.setAttribute("loginParamsMap",loginParamsMap);
//			
//			RasSessionAttributes rsa = new RasSessionAttributes();
//			rsa.setName("RaahulTest");
//			session.setAttribute("rsa", rsa);
//			
//			System.out.println(session.getAttribute("loginParamsMap"));
////		}
//		
//		return "Successful";
//	}
//	
//	public Map<String, String> getLoginParams(HttpServletRequest request){
//		
//		System.out.println("Inside class: HttpSessionService & method : getLoginParams() ");
//		HttpSession session = request.getSession(false);
//		
//		Map<String, String> sessionLoginParams = new HashMap<String, String>();
//		sessionLoginParams= (Map<String, String>) session.getAttribute("loginParamsMap");
//		
//		return sessionLoginParams;
//	}
//	
//	
//	
//}

package com.ras;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ras.resource.DepartmentConfigRepository;
import com.ras.resource.InstituteConfigRepository;
import com.ras.resource.LoginTypeConfigRepository;
import com.ras.resource.ResearchRepository;
import com.ras.resource.UserAccountRepository;



@SpringBootApplication
public class RasApplication implements CommandLineRunner{
	
	@Autowired
	private ResearchRepository researchRepository;
	
	@Autowired
	private LoginTypeConfigRepository loginTypeConfigRepository;
	
	@Autowired
	private InstituteConfigRepository instituteConfigRepository;
	
	@Autowired
	private DepartmentConfigRepository departmentConfigRepository;
	
//	@Autowired
//	private LoginRequestRepository loginRequestRepository;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	
//	@GetMapping("RAS")
//	public String RAS() {
//		return "Application Deployed";
//	}

	public static void main(String[] args){
		
		SpringApplication.run(RasApplication.class, args);
			    
	}

	@Override
	public void run(String... args) throws Exception {
//		LoginRequest r1 = new LoginRequest("Harshada", "Bhangale","hp@gmail.com", "1234",1,0,0,"",new java.util.Date(),new java.util.Date());
//		LoginRequest r2 = new LoginRequest("Rahul", "Bhole","rb@gmail.com", "1234",2,1,1,"pending",new java.util.Date(),new java.util.Date());
//		LoginRequest r3 = new LoginRequest("Pooja", "Bhangale","pb@gmail.com", "1234",2,1,1,"pending",new java.util.Date(),new java.util.Date());
//		
//		
//		LoginRequest r4 = new LoginRequest("Harita", "Bhangale","hp@gmail.com", "1234",2,1,2,"pending",new java.util.Date(),new java.util.Date());
//		LoginRequest r5 = new LoginRequest("Yuga", "Bhole","rb@gmail.com", "1234",2,2,1,"pending",new java.util.Date(),new java.util.Date());
//		LoginRequest r6 = new LoginRequest("Pranathi", "Bhangale","pb@gmail.com","1234",3,1,1,"pending",new java.util.Date(),new java.util.Date());
//		
//		
//		loginRequestRepository.save(r1);
//		loginRequestRepository.save(r2);
//		loginRequestRepository.save(r3);
//		loginRequestRepository.save(r4);
//		loginRequestRepository.save(r5);
//		loginRequestRepository.save(r6);
		
//		
//		LoginTypeConfig l1 = new LoginTypeConfig(1,"admin");
//		LoginTypeConfig l2 = new LoginTypeConfig(2,"researcher");
//		LoginTypeConfig l3 = new LoginTypeConfig(3,"reviewer");
//		
//		loginTypeConfigRepository.save(l1);
//		loginTypeConfigRepository.save(l2);
//		loginTypeConfigRepository.save(l3);
//		
//		InstituteConfig i1 = new InstituteConfig(1,"CSUF_cancer");
//		InstituteConfig i2 = new InstituteConfig(2,"CSUF_diabetes");
//		
//		instituteConfigRepository.save(i1);
//		instituteConfigRepository.save(i2);
//		
//		DepartmentConfig d1 = new DepartmentConfig(1,1,"blood");
//		DepartmentConfig d2 = new DepartmentConfig(2,1,"bone");
//		DepartmentConfig d3 = new DepartmentConfig(3,1,"brain");
//		DepartmentConfig d4 = new DepartmentConfig(4,2,"type1");
//		DepartmentConfig d5 = new DepartmentConfig(5,2,"type2");
//		DepartmentConfig d6 = new DepartmentConfig(6,2,"gestational");
//		
//		departmentConfigRepository.save(d1);
//		departmentConfigRepository.save(d2);
//		departmentConfigRepository.save(d3);
//		departmentConfigRepository.save(d4);
//		departmentConfigRepository.save(d5);
//		departmentConfigRepository.save(d6);
		
		
		
		
	}

}

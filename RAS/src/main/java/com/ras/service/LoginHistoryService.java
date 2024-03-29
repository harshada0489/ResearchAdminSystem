package com.ras.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import com.ras.model.LoginHistory;
import com.ras.repository.LoginHistoryRepository;
import com.ras.service.mongodbOperations.NextSequenceService;

@Service
public class LoginHistoryService {

	@Autowired
	LoginHistoryRepository loginHistoryRepository;
	
	@Autowired
	NextSequenceService nextSequenceService;
	
	
	public LoginHistoryService() {
		// TODO Auto-generated constructor stub
	}

	
	public void insertLoginDetails(int userId, String ipAddress, Date loginTime) {
		
		int seq = nextSequenceService.getNextSequenceForloginHistoryIdSeq("customSequences");
		System.out.println("loginHistoryId generated" + seq);
		
		

		 
		LoginHistory loginHistory = new LoginHistory(seq, userId, ipAddress, loginTime, null);
		loginHistoryRepository.save(loginHistory);
	
	}
	
	public LoginHistory getLastLoggedIn(int userId) {
		LoginHistory loginHistory = null;
		List<LoginHistory> loginByUserList = loginHistoryRepository.findByUserIdOrderByIdDesc(userId);
		if(loginByUserList.size()>1) {
			loginHistory = loginByUserList.get(1);
		}
		return loginHistory;
	}
	
}

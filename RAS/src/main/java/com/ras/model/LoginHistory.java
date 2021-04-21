package com.ras.model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("session")
@Document(collection = "loginHistory")
public class LoginHistory {

	@Id
	private int id;
	 
	private int userId;
	
	private String ipAddress;
	
	private Date loginTime;
	
	private Date logoutTime;
	 
	 
	 
	 






	public LoginHistory(int id, int userId, String ipAddress, Date loginTime, Date logoutTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.ipAddress = ipAddress;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	public Date getLoginTime() {
		return loginTime;
	}




	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}




	public Date getLogoutTime() {
		return logoutTime;
	}




	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}




	public String getIpAddress() {
		return ipAddress;
	}




	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	
	
}

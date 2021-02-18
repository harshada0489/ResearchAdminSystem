package com.ras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

@Entity
public class LoginTypeConfig {
	
	@Id
	@GeneratedValue
	private int loginId;
	private String loginType;
	
	public LoginTypeConfig() {
		// TODO Auto-generated constructor stub
	}

	

	public LoginTypeConfig(int loginId, String loginType) {
		super();
		this.loginId = loginId;
		this.loginType = loginType;
	}



	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Override
	public String toString() {
		return "LoginTypeConfig [loginId=" + loginId + ", loginType=" + loginType + "]";
	}
	
	

}

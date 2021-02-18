package com.ras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


import org.springframework.data.annotation.Id;

@Entity
public class Research {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String pwd;
	private String reEnterPwd;
	private String loginType;
	
	public void Research() {
		
	}

	
	public Research(int id, String firstName, String lastName, String emailId, String pwd, String reEnterPwd,
			String loginType) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.pwd = pwd;
		this.reEnterPwd = reEnterPwd;
		this.loginType = loginType;
	}


	public Research() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getReEnterPwd() {
		return reEnterPwd;
	}

	public void setReEnterPwd(String reEnterPwd) {
		this.reEnterPwd = reEnterPwd;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Override
	public String toString() {
		return "Research [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", pwd=" + pwd + ", reEnterPwd=" + reEnterPwd + ", loginType=" + loginType + "]";
	}
	
	
	

}

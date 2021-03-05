package com.ras.model;

import javax.persistence.GeneratedValue;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


import org.springframework.data.annotation.Id;

@Entity
@Component
@Scope("session")
public class UserAccount {

	
	@Id
	@GeneratedValue
	private int id;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String pwd;
	private String loginType;
	private String institute;
	private String department;
	private String status;
	private Date createdDate;
	private Date modifiedDate;

	
	public UserAccount() {
		// TODO Auto-generated constructor stub
	}

	

	public UserAccount(int id, String firstName, String lastName, String emailId, String pwd, String loginType,
			String institute, String department, String status, Date createdDate, Date modifiedDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.pwd = pwd;
		this.loginType = loginType;
		this.institute = institute;
		this.department = department;
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
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


	public String getLoginType() {
		return loginType;
	}


	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}


	public String getInstitute() {
		return institute;
	}


	public void setInstitute(String institute) {
		this.institute = institute;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", pwd=" + pwd + ", loginType=" + loginType + ", institute=" + institute + ", department="
				+ department + ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ "]";
	}



	
}

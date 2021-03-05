package com.ras.model.payload.request;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
//
//@Entity
////@Table(name="LoginRequest")
//public class LoginRequest {
//	
//	
//	
//	@MongoId
////	@GeneratedValue(strategy = GenerationType.TABLE)
//	private String id;
//	
////	@Column(name = "firstName")
//	@JsonProperty(value = "firstName")
//	private String firstName;
//	
//	@JsonProperty(value = "lastName")
//	private String lastName;
//	
//	@JsonProperty(value = "email")
//	private String emailId;
//	
//	@JsonProperty(value = "pwd")
//	private String pwd;
//	
//	@JsonProperty(value = "loginType")
//	private String loginType;
//	
//	@JsonProperty(value = "institute")
//	private String institute;
//	
//	@JsonProperty(value = "department")
//	private String department;
//	
//	private String status;
//	
//	private Date createdDate;
//	
//	private Date modifiedDate;
//
//
//	public LoginRequest() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public LoginRequest(String firstName, String lastName, String emailId, String pwd, String loginType,
//			String institute, String department, String status, Date createdDate, Date modifiedDate) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.emailId = emailId;
//		this.pwd = pwd;
//		this.loginType = loginType;
//		this.institute = institute;
//		this.department = department;
//		this.status = status;
//		this.createdDate = createdDate;
//		this.modifiedDate = modifiedDate;
//	}
//
//	
//
//	
//
//
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	@JsonProperty(value = "firstName")
//	public String getFirstName() {
//		return firstName;
//	}
//
//	@JsonProperty(value = "firstName")
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//
//	@JsonProperty(value = "lastName")
//	public String getLastName() {
//		return lastName;
//	}
//
//	@JsonProperty(value = "lastName")
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	@JsonProperty(value = "email")
//	public String getEmailId() {
//		return emailId;
//	}
//
//
//	@JsonProperty(value = "email")
//	public void setEmailId(String emailId) {
//		this.emailId = emailId;
//	}
//
//	
//	@JsonProperty(value = "pwd")
//	public String getPwd() {
//		return pwd;
//	}
//
//	@JsonProperty(value = "pwd")
//	public void setPwd(String pwd) {
//		this.pwd = pwd;
//	}
//
//	@JsonProperty(value = "loginType")
//	public String getLoginType() {
//		return loginType;
//	}
//
//	@JsonProperty(value = "loginType")
//	public void setLoginType(String loginType) {
//		this.loginType = loginType;
//	}
//
//	
//	@JsonProperty(value = "institute")
//	public String getInstitute() {
//		return institute;
//	}
//
//
//	@JsonProperty(value = "institute")
//	public void setInstitute(String institute) {
//		this.institute = institute;
//	}
//
//	@JsonProperty(value = "department")
//	public String getDepartment() {
//		return department;
//	}
//
//
//	@JsonProperty(value = "department")
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//
//	public String getStatus() {
//		return status;
//	}
//
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//
//	public Date getModifiedDate() {
//		return modifiedDate;
//	}
//
//	public void setModifiedDate(Date modifiedDate) {
//		this.modifiedDate = modifiedDate;
//	}
//
//	@Override
//	public String toString() {
//		return "LoginRequest [ firstName=" + firstName + ", lastName=" + lastName
//				+ ", emailId=" + emailId + ", pwd=" + pwd + ", loginType=" + loginType + ", institute=" + institute
//				+ ", department=" + department + ", status=" + status + ", createdDate=" + createdDate
//				+ ", modifiedDate=" + modifiedDate + "]";
//	}

//}

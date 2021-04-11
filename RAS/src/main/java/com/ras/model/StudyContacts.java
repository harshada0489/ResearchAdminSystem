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
@Document(collection = "studyContacts")

public class StudyContacts {

	@Id
	private int id;
	
	private int studyAppId;
	
	private int studyDataFormId;
	
	private int userId;
	
	private int rbId;
	
	private int type;
	
	
	private int creatorId;
	
	private String status;
	private Date createdDate;
	private Date modifiedDate;
	
	public StudyContacts() {
		// TODO Auto-generated constructor stub
	}
	
	

	public StudyContacts(int studyAppId, int studyDataFormId, int userId, int rvId, int type) {
		super();
		this.studyAppId = studyAppId;
		this.studyDataFormId = studyDataFormId;
		this.userId = userId;
		this.rbId = rvId;
		this.type = type;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudyAppId() {
		return studyAppId;
	}

	public void setStudyAppId(int studyAppId) {
		this.studyAppId = studyAppId;
	}

	public int getStudyDataFormId() {
		return studyDataFormId;
	}

	public void setStudyDataFormId(int studyDataFormId) {
		this.studyDataFormId = studyDataFormId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRvId() {
		return rbId;
	}

	public void setRvId(int rvId) {
		this.rbId = rvId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	
	
	
}

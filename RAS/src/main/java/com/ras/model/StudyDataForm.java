package com.ras.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studyDataForm")
public class StudyDataForm {

	@Id
	private String id;	

	private String studyId;
	
	private String systemFormId;
	
	private String studyAppDataId;

	private String systemFormName;
	

	private String status;
	
	private Date createdDate;
	
	private Date modifiedDate;

	


	public StudyDataForm(String studyId, String systemFormId, String studyAppDataId) {
		super();
		this.studyId = studyId;
		this.systemFormId = systemFormId;
		this.studyAppDataId = studyAppDataId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	public String getSystemFormId() {
		return systemFormId;
	}

	public void setSystemFormId(String systemFormId) {
		this.systemFormId = systemFormId;
	}

	public String getSystemFormName() {
		return systemFormName;
	}

	public void setSystemFormName(String systemFormName) {
		this.systemFormName = systemFormName;
	}
	
	

	public String getStudyAppDataId() {
		return studyAppDataId;
	}

	public void setStudyAppDataId(String studyAppDataId) {
		this.studyAppDataId = studyAppDataId;
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
	
	
	
	

}

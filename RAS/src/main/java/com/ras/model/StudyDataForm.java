package com.ras.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studyDataForm")
public class StudyDataForm {

	@Id
	private int id;	

	private int studyAppId;
	
	private int systemFormId;
	
	private int dynamicTableDataId;

	private String dynamicTableName;
	
	private String dynamicTableFormVersion;
	
	private String round;
	
	private boolean isLock;
	
	private int creatorId;
	
	private String status;
	
	private Date createdDate;
	
	private Date modifiedDate;

	public StudyDataForm() {
		
	}


	public StudyDataForm(int studyAppId, int systemFormId, String dynamicTableName) {
		super();
		this.studyAppId = studyAppId;
		this.systemFormId = systemFormId;
		this.dynamicTableName = dynamicTableName;
	}

	public int getId() {
		return id;
	}

	public void setId(int seq) {
		this.id = seq;
	}


	public int getStudyAppId() {
		return studyAppId;
	}

	public void setStudyAppId(int studyAppId) {
		this.studyAppId = studyAppId;
	}

	public int getSystemFormId() {
		return systemFormId;
	}

	public void setSystemFormId(int systemFormId) {
		this.systemFormId = systemFormId;
	}

	public int getDynamicTableDataId() {
		return dynamicTableDataId;
	}

	public void setDynamicTableDataId(int dynamicTableDataId) {
		this.dynamicTableDataId = dynamicTableDataId;
	}

	public String getDynamicTableName() {
		return dynamicTableName;
	}

	public void setDynamicTableName(String dynamicTableName) {
		this.dynamicTableName = dynamicTableName;
	}

	public String getDynamicTableFormVersion() {
		return dynamicTableFormVersion;
	}

	public void setDynamicTableFormVersion(String dynamicTableFormVersion) {
		this.dynamicTableFormVersion = dynamicTableFormVersion;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
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


	public boolean isLock() {
		return isLock;
	}


	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	
	

}

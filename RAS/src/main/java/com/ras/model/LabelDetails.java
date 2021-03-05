package com.ras.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Document(collection = "LabelDetails")
@Entity
@Component
@Scope("session")
public class LabelDetails {

	@MongoId
	  private String id;

	  @NotBlank
	  @JsonProperty(value = "ipType")
	  private String ipType;

	  @NotBlank
	  @JsonProperty(value = "labelName")
	  private String labelName;

	  @NotBlank
	  @JsonProperty(value = "seqNum")
	  private String seqNum;
	  
	  private String studyField;
	  
	  private String studyType;
	  
	  private Date createdDate;
		
	  private Date modifiedDate;
		

	  public LabelDetails() {
		  
	  }


	public LabelDetails(String id, @NotBlank String ipType, @NotBlank String labelName, @NotBlank String seqNum,
			String studyField, String studyType, Date createdDate, Date modifiedDate) {
		super();
		this.id = id;
		this.ipType = ipType;
		this.labelName = labelName;
		this.seqNum = seqNum;
		this.studyField = studyField;
		this.studyType = studyType;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}


	public String getStudyField() {
		return studyField;
	}


	public void setStudyField(String studyField) {
		this.studyField = studyField;
	}


	public String getStudyType() {
		return studyType;
	}


	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty(value = "ipType")
	public String getIpType() {
		return ipType;
	}
	
	@JsonProperty(value = "ipType")
	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	@JsonProperty(value = "labelName")
	public String getLabelName() {
		return labelName;
	}

	@JsonProperty(value = "labelName")
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@JsonProperty(value = "seqNum")
	public String getSeqNum() {
		return seqNum;
	}

	@JsonProperty(value = "seqNum")
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
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
		return "LabelDetails [id=" + id + ", ipType=" + ipType + ", labelName=" + labelName + ", seqNum=" + seqNum
				+ "]";
	}

	  

}

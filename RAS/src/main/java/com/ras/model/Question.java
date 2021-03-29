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
@Document(collection = "question")
public class Question {

	  @Id
	  private int id;

	@NotBlank
	  @JsonProperty(value = "formId")
	  private int formId;
	
	  @NotBlank
	  @JsonProperty(value = "pageNumber")
	  private String pageNumber;

	  @NotBlank
	  @JsonProperty(value = "questionNumber")
	  private String questionNumber;
	  
	  @NotBlank
	  @JsonProperty(value = "questionText")
	  private String questionText;
	  
	  @NotBlank
	  @JsonProperty(value = "answerType")
	  private String answerType;
	  
	  @NotBlank
	  @JsonProperty(value = "dbColumnName")
	  private String dbColumnName;
	  
	  private String lengthOfAnswer;
	  
	  @NotBlank
	  @JsonProperty(value = "pageId")
	  private int pageId;
	  
	  private String createdByUserId;
	  
	  private String modifiedByUserId;
	  
	  
	  private String status;
	  
	  
	  private Date createdDate;
		
	  private Date modifiedDate;

	  
	  
	  
	  
//	public Question(@NotBlank String formId, @NotBlank String pageNumber, @NotBlank String questionNum,
//			@NotBlank String answerType) {
//		super();
//		this.formId = formId;
//		this.pageNumber = pageNumber;
//		this.questionNum = questionNum;
//		this.answerType = answerType;
//	}
	
		public Question(@NotBlank int formId, @NotBlank String pageNumber, @NotBlank String questionNumber,
				@NotBlank String questionText, @NotBlank String answerType, @NotBlank String dbColumnName,
				String lengthOfAnswer, @NotBlank int pageId) {
			super();
			this.formId = formId;
			this.pageNumber = pageNumber;
			this.questionNumber = questionNumber;
			this.questionText = questionText;
			this.answerType = answerType;
			this.dbColumnName = dbColumnName;
			this.lengthOfAnswer = lengthOfAnswer;
			this.pageId = pageId;
		}




		public int getId() {
			return id;
		}

		public void setId(int seq) {
			this.id = seq;
		}
		
	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
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

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getLengthOfAnswer() {
		return lengthOfAnswer;
	}

	public void setLengthOfAnswer(String lengthOfAnswer) {
		this.lengthOfAnswer = lengthOfAnswer;
	}

	public String getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public String getModifiedByUserId() {
		return modifiedByUserId;
	}

	public void setModifiedByUserId(String modifiedByUserId) {
		this.modifiedByUserId = modifiedByUserId;
	}






	public int getPageId() {
		return pageId;
	}




	public void setPageId(int pageId) {
		this.pageId = pageId;
	}




	public String getQuestionNumber() {
		return questionNumber;
	}



	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}






}

package com.ras.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Component
@Scope("session")
public class Page {

	@MongoId
	  private String id;

	  
	  @NotBlank
	  @JsonProperty(value = "pageNumber")
	  private int pageNumber;
	  
	  
	  @NotBlank
	  @JsonProperty(value = "formId")
	  private String formId;
	  
	 
	  private String status;
	  
	  private Date createdDate;
		
	  private Date modifiedDate;
	  
	  
	
	public Page(@NotBlank int pageNumber, @NotBlank String formId) {
		super();
		this.pageNumber = pageNumber;
		this.formId = formId;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
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

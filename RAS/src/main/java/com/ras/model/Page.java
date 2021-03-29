package com.ras.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Component
@Scope("session")
@Document(collection = "page")
public class Page {

	@Id
	  private int id;

	  
	  @NotBlank
	  @JsonProperty(value = "pageNumber")
	  private int pageNumber;
	  
	  
	  @NotBlank
	  @JsonProperty(value = "formId")
	  private int formId;
	  
	  private String formVersion;
	 
	  private String status;
	  
	  private Date createdDate;
		
	  private Date modifiedDate;
	  
	  
	
	public Page(@NotBlank int pageNumber, @NotBlank int formId) {
		super();
		this.pageNumber = pageNumber;
		this.formId = formId;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFormVersion() {
		return formVersion;
	}


	public void setFormVersion(String formVersion) {
		this.formVersion = formVersion;
	}



	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}



	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
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

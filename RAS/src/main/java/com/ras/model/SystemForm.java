package com.ras.model;



import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "systemForm")
public class SystemForm {
	 
	  @Id
	  private int id;

	  
	  @NotBlank
	  private String filterList;
	   

	  @NotBlank
	  private String formName;

	  @NotBlank
	  private String formDescription;
	  
	  
	  private String dynamicTableName;

	  private int version;
	  
	  
	  private int creatorId;
	  
	  private String status;
  
	  private Date createdDate;
	  
	  private Date modifiedDate;
	  
	


public SystemForm(@NotBlank String formName,@NotBlank String formDescription) {
		super();
		this.formName = formName;
		this.formDescription = formDescription;
	}

//	public SystemForm(String id, @NotBlank String filterList, String status, @NotBlank String formName,
//			String formDescription, Date createdDate, Date modifiedDate) {
//		super();
//		this.id = id;
//		this.filterList = filterList;
//		this.status = status;
//		this.formName = formName;
//		this.formDescription = formDescription;
//		this.createdDate = createdDate;
//		this.modifiedDate = modifiedDate;
//	}


	public int getId() {
		return id;
	}

	public void setId(int seq) {
		this.id = seq;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormDescription() {
		return formDescription;
	}

	public void setFormDescription(String formDescription) {
		this.formDescription = formDescription;
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


	public String getFilterList() {
		return filterList;
	}



	public void setFilterList(String filterList) {
		this.filterList = filterList;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getDynamicTableName() {
		return dynamicTableName;
	}

	public void setDynamicTableName(String dynamicTableName) {
		this.dynamicTableName = dynamicTableName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	  

}

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
	  private String id;

	  @NotBlank
	  private String filterList;
	   
	  private String status;
	  
	  @NotBlank
	  private String formName;

	  private String formDescription;

	  	  
	  private Date createdDate;
	  
	  private Date modifiedDate;
	  
	
	public SystemForm(@NotBlank String filterList, @NotBlank String formName, String formDescription) {
		super();
		this.filterList = filterList;
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	  

}

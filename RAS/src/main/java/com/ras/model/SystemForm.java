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
	  private String formName;

	  private String formDescription;

	  private boolean isDeleted;
	  
	  private Date createdDate;
	  
	  private Date modifiedDate;
	  
	public SystemForm(@NotBlank String formName, String formDescription) {
		super();
		this.formName = formName;
		this.formDescription = formDescription;
	}
	
	
	
	

//	public SystemForm(@NotBlank String formName, String formDescription, boolean isDeleted, Date createdDate,
//			Date modifiedDate) {
//		super();
//		this.formName = formName;
//		this.formDescription = formDescription;
//		this.isDeleted = isDeleted;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

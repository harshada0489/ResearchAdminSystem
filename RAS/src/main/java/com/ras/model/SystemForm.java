package com.ras.model;

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

	  
	  
	public SystemForm(@NotBlank String formName, String formDescription) {
		super();
		this.formName = formName;
		this.formDescription = formDescription;
	}

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

	  

}

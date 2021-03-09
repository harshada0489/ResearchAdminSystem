package com.ras.model;



import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class FormDetails {
	 
	  
	  private String filter1;
	  
	  private String filter2;
	  
	  private String formName;

	  private String formDescription;

	  
	  
	  
	public FormDetails(String filter1, String filter2, String formName, String formDescription) {
		super();
		this.filter1 = filter1;
		this.filter2 = filter2;
		this.formName = formName;
		this.formDescription = formDescription;
	}
	
	

	public String getFilter1() {
		return filter1;
	}

	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}

	public String getFilter2() {
		return filter2;
	}

	public void setFilter2(String filter2) {
		this.filter2 = filter2;
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

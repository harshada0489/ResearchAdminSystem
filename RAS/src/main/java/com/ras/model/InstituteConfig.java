package com.ras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

@Entity
public class InstituteConfig {
	
	@Id
	@GeneratedValue
	private int instituteId;
	private String instituteName;
	
	public InstituteConfig() {
		// TODO Auto-generated constructor stub
	}

	
	public InstituteConfig(int instituteId, String instituteName) {
		super();
		this.instituteId = instituteId;
		this.instituteName = instituteName;
	}


	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	@Override
	public String toString() {
		return "InstituteConfig [instituteId=" + instituteId + ", instituteName=" + instituteName + "]";
	}
	
	

}

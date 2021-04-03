package com.ras.model;

import javax.persistence.Entity;

@Entity
public class StudyContactsConfig {

	
	private int Id;
	
	private String type;
	
	public StudyContactsConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public StudyContactsConfig(int id, String type) {
		super();
		Id = id;
		this.type = type;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

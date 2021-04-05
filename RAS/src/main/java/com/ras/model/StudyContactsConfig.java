package com.ras.model;

import javax.persistence.Entity;

@Entity
public class StudyContactsConfig {

	
	private int Id;
	
	private int type;
	
	public StudyContactsConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public StudyContactsConfig(int id, int type) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}

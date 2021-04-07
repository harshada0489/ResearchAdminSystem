package com.ras.model;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "studyContactsConfig")
public class StudyContactsConfig {

	@Id
	private int id;
	
	private String type;
	


	public StudyContactsConfig(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}





	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}


	

}

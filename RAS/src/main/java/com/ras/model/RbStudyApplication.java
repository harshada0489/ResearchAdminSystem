package com.ras.model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "RbStudyApplication")
public class RbStudyApplication {

	@Id
	private int id;
	
	private String studyTitle;
	
	private String oneWordIdentifier;

	private String gender;
	
	private int age;
	
	private int NoOfHumanRequired;

	private String typeOfAnimal;
	
	private int NoOfAnimalRequired;

	private String filter1;
	
	private String filter2;

	private String researcherID;
	
	private int creatorId;
	
	private int rbId;
	
	private String type;
	
	private String status;
	
	private Date createdDate;
	
	private Date modifiedDate;

	
	public RbStudyApplication() {
		// TODO Auto-generated constructor stub
	}
	
	


	public RbStudyApplication(String studyTitle, String oneWordIdentifier, String gender, int age,
			int noOfHumanRequired, String typeOfAnimal, int noOfAnimalRequired, String filter1, String filter2,
			String researcherID, int creatorId, int rbId, String type) {
		super();
		this.studyTitle = studyTitle;
		this.oneWordIdentifier = oneWordIdentifier;
		this.gender = gender;
		this.age = age;
		NoOfHumanRequired = noOfHumanRequired;
		this.typeOfAnimal = typeOfAnimal;
		NoOfAnimalRequired = noOfAnimalRequired;
		this.filter1 = filter1;
		this.filter2 = filter2;
		this.researcherID = researcherID;
		this.creatorId = creatorId;
		this.rbId = rbId;
		this.type = type;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStudyTitle() {
		return studyTitle;
	}


	public void setStudyTitle(String studyTitle) {
		this.studyTitle = studyTitle;
	}


	public String getOneWordIdentifier() {
		return oneWordIdentifier;
	}


	public void setOneWordIdentifier(String oneWordIdentifier) {
		this.oneWordIdentifier = oneWordIdentifier;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getNoOfHumanRequired() {
		return NoOfHumanRequired;
	}


	public void setNoOfHumanRequired(int noOfHumanRequired) {
		NoOfHumanRequired = noOfHumanRequired;
	}


	public String getTypeOfAnimal() {
		return typeOfAnimal;
	}


	public void setTypeOfAnimal(String typeOfAnimal) {
		this.typeOfAnimal = typeOfAnimal;
	}


	public int getNoOfAnimalRequired() {
		return NoOfAnimalRequired;
	}


	public void setNoOfAnimalRequired(int noOfAnimalRequired) {
		NoOfAnimalRequired = noOfAnimalRequired;
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


	public String getResearcherID() {
		return researcherID;
	}


	public void setResearcherID(String researcherID) {
		this.researcherID = researcherID;
	}


	public int getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}


	public int getRbId() {
		return rbId;
	}


	public void setRbId(int rbId) {
		this.rbId = rbId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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

package com.ras.model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CreateStudy {

	@MongoId
//	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	
//	@Column(name = "firstName")
	@JsonProperty(value = "studyTitle")
	private String studyTitle;
	
	@JsonProperty(value = "oneWordIdentifier")
	private String oneWordIdentifier;
	
	@JsonProperty(value = "gender")
	private String gender;
	
	@JsonProperty(value = "age")
	private int age;
	
	@JsonProperty(value = "NoOfHumanRequired")
	private int NoOfHumanRequired;
	
	@JsonProperty(value = "typeOfAnimal")
	private String typeOfAnimal;
	
	@JsonProperty(value = "NoOfAnimalRequired")
	private int NoOfAnimalRequired;
	
	
	@JsonProperty(value = "selectedStudyOption")
	private String selectedStudyOption;
	
	@JsonProperty(value = "selectedStudyField")
	private String selectedStudyField;
	
	@JsonProperty(value = "researcherId")
	private String researcherID;
	
	private String status;
	
	private Date createdDate;
	
	private Date modifiedDate;

	
	public CreateStudy() {
		// TODO Auto-generated constructor stub
	}







	public CreateStudy(String id, String studyTitle, String oneWordIdentifier, String gender, int age,
			int noOfHumanRequired, String typeOfAnimal, int noOfAnimalRequired, String selectedStudyOption,
			String selectedStudyField, String researcherID, String status, Date createdDate, Date modifiedDate) {
		super();
		this.id = id;
		this.studyTitle = studyTitle;
		this.oneWordIdentifier = oneWordIdentifier;
		this.selectedStudyOption = selectedStudyOption;
		this.selectedStudyField = selectedStudyField;
		this.gender = gender;
		this.age = age;
		this.NoOfHumanRequired = noOfHumanRequired;
		this.typeOfAnimal = typeOfAnimal;
		this.NoOfAnimalRequired = noOfAnimalRequired;
		this.researcherID = researcherID;
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}


	



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@JsonProperty(value = "studyTitle")
	public String getStudyTitle() {
		return studyTitle;
	}

	@JsonProperty(value = "studyTitle")
	public void setStudyTitle(String studyTitle) {
		this.studyTitle = studyTitle;
	}

	@JsonProperty(value = "oneWordIdentifier")
	public String getOneWordIdentifier() {
		return oneWordIdentifier;
	}

	@JsonProperty(value = "oneWordIdentifier")
	public void setOneWordIdentifier(String oneWordIdentifier) {
		this.oneWordIdentifier = oneWordIdentifier;
	}

	@JsonProperty(value = "selectedStudyOption")
	public String getSelectedStudyOption() {
		return selectedStudyOption;
	}


	@JsonProperty(value = "selectedStudyOption")
	public void setSelectedStudyOption(String selectedStudyOption) {
		this.selectedStudyOption = selectedStudyOption;
	}


	@JsonProperty(value = "selectedStudyField")
	public String getSelectedStudyField() {
		return selectedStudyField;
	}


	@JsonProperty(value = "selectedStudyField")
	public void setSelectedStudyField(String selectedStudyField) {
		this.selectedStudyField = selectedStudyField;
	}
	
	@JsonProperty(value = "gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty(value = "gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty(value = "age")
	public int getAge() {
		return age;
	}

	@JsonProperty(value = "age")
	public void setAge(int age) {
		this.age = age;
	}

	@JsonProperty(value = "NoOfHumanRequired")
	public int getNoOfHumanRequired() {
		return NoOfHumanRequired;
	}

	@JsonProperty(value = "NoOfHumanRequired")
	public void setNoOfHumanRequired(int noOfHumanRequired) {
		NoOfHumanRequired = noOfHumanRequired;
	}

	@JsonProperty(value = "typeOfAnimal")
	public String getTypeOfAnimal() {
		return typeOfAnimal;
	}

	@JsonProperty(value = "typeOfAnimal")
	public void setTypeOfAnimal(String typeOfAnimal) {
		this.typeOfAnimal = typeOfAnimal;
	}

	@JsonProperty(value = "NoOfAnimalRequired")
	public int getNoOfAnimalRequired() {
		return NoOfAnimalRequired;
	}

	@JsonProperty(value = "NoOfAnimalRequired")
	public void setNoOfAnimalRequired(int noOfAnimalRequired) {
		NoOfAnimalRequired = noOfAnimalRequired;
	}

	@JsonProperty(value = "researcherID")
	public String getResearcherID() {
		return researcherID;
	}

	@JsonProperty(value = "researcherID")
	public void setResearcherID(String researcherID) {
		this.researcherID = researcherID;
	}

	@JsonProperty(value = "status")
	public String getStatus() {
		return status;
	}

	@JsonProperty(value = "status")
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



	@Override
	public String toString() {
		return "CreateStudy [id=" + id + ", studyTitle=" + studyTitle + ", oneWordIdentifier=" + oneWordIdentifier
				+ ", gender=" + gender + ", age=" + age + ", NoOfHumanRequired=" + NoOfHumanRequired + ", typeOfAnimal="
				+ typeOfAnimal + ", NoOfAnimalRequired=" + NoOfAnimalRequired + ", selectedStudyField="
				+ selectedStudyField + ", researcherID=" + researcherID + ", status=" + status + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}



	

	
	
}

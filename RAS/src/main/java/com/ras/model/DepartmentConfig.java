package com.ras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

@Entity
public class DepartmentConfig {
	
	@Id
	@GeneratedValue
	private int departmentId;
	
	private String departmentName;
	private int instituteId;
	
	public DepartmentConfig() {
		// TODO Auto-generated constructor stub
	}

	
	public DepartmentConfig(int departmentId, int instituteId,String departmentName) {
		super();
		this.departmentId = departmentId;
		this.instituteId = instituteId;
		this.departmentName = departmentName;
	}


	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	@Override
	public String toString() {
		return "DepartmentConfig [departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", instituteId=" + instituteId + "]";
	}
	
	

}

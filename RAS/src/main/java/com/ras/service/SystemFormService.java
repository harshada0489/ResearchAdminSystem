package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.SystemForm;
import com.ras.repository.SystemFormRepository;

@Service
public class SystemFormService {

	@Autowired
	SystemFormRepository repository;
	
	public List<SystemForm> getAllDBForms(){
		System.out.println("Inside class: SystemFormService and method: getAllDBForms() ");
		List<SystemForm> dbAllForms = repository.findAll();
		return dbAllForms;
	}

}

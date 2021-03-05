package com.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.model.CreateStudy;
import com.ras.model.LabelDetails;
import com.ras.repository.LabelDetailsRepository;
import com.ras.resource.CreateStudyRepository;

@Service
public class LabelDetailsService {

	@Autowired
	LabelDetailsRepository labelDetailsRepository;
	
	public LabelDetailsService() {
		// TODO Auto-generated constructor stub
	}

	public String addLabelDetailsWithDefaultValues(List<LabelDetails> labelDetails) {
		System.out.println("Calling from class: LabelDetailsService & method: addLabelDetailsWithDefaultValues() ");
			for(int counter=0; counter<labelDetails.size(); counter++) {
				LabelDetails lb=labelDetails.get(counter);
				lb.setCreatedDate(new java.util.Date());
				lb.setModifiedDate(new java.util.Date());

				labelDetailsRepository.save(lb);
			}
		
		return "Successful";
	}
	
}

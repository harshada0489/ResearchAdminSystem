package com.ras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.StudyDataForm;
import com.ras.model.SystemForm;

public interface StudyDataFormRepository extends MongoRepository<StudyDataForm, Integer> {

	List<StudyDataForm> findByStudyAppIdOrderByIdDesc(Integer studyAppId) ;
	

}

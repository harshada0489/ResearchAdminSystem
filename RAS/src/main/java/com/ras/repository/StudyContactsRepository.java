package com.ras.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.StudyContacts;

public interface StudyContactsRepository extends MongoRepository<StudyContacts, Integer> {

	StudyContacts findByStudyAppIdAndType(Integer studyAppId, Integer type);
	
	List<StudyContacts> findByStudyDataFormId(int studyDataFormId);
}

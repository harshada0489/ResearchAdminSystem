package com.ras.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.StudyDataForm;
import com.ras.model.SystemForm;

public interface StudyDataFormRepository extends MongoRepository<StudyDataForm, String> {

}

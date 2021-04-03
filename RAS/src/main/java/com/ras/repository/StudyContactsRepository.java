package com.ras.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.StudyContacts;

public interface StudyContactsRepository extends MongoRepository<StudyContacts, Integer> {

}

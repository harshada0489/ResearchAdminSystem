package com.ras.resource;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ras.model.CreateStudy;

@Repository
public interface CreateStudyRepository extends MongoRepository<CreateStudy, Integer> {

}

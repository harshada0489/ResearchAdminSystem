package com.ras.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.LoginHistory;


public interface LoginHistoryRepository extends MongoRepository<LoginHistory, Integer> {

}

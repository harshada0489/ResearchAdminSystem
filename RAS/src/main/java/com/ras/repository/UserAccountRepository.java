package com.ras.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ras.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount,Integer>{

}

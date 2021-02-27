package com.banking.bankservice.dao;

import com.banking.bankservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {
}

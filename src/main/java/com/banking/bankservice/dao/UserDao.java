package com.banking.bankservice.dao;

import com.banking.bankservice.model.User;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, ObjectId> {
    Optional<User> getByPhoneNumber(String phoneNumber);
}

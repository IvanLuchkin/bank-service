package com.banking.bankservice.repository;

import com.banking.bankservice.model.User;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> getByPhoneNumber(String phoneNumber);
}

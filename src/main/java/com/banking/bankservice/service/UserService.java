package com.banking.bankservice.service;

import com.banking.bankservice.model.User;
import java.util.Optional;
import org.bson.types.ObjectId;

public interface UserService {
    User add(User user);

    Optional<User> getById(ObjectId id);

    User update(User user);

    void delete(User user);
}

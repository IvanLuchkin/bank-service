package com.banking.bankservice.service;

import com.banking.bankservice.model.User;
import org.bson.types.ObjectId;

public interface UserService {
    User add(User user);

    User getById(ObjectId id);

    User findByPhoneNumber(String phoneNumber);

    User update(User user);

    void delete(User user);
}

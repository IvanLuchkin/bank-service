package com.banking.bankservice.service;

import com.banking.bankservice.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> getById(String id);

    User update(User user);

    void delete(User user);
}

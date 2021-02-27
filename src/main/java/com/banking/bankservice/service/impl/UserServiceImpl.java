package com.banking.bankservice.service.impl;

import com.banking.bankservice.dao.UserDao;
import com.banking.bankservice.model.User;
import com.banking.bankservice.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> getById(ObjectId id) {
        return userDao.findById(id);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}

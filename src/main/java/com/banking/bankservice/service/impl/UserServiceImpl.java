package com.banking.bankservice.service.impl;

import com.banking.bankservice.dao.UserDao;
import com.banking.bankservice.model.User;
import com.banking.bankservice.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> getById(String id) {
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

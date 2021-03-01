package com.banking.bankservice.service.impl;

import com.banking.bankservice.exception.EntityNotFoundException;
import com.banking.bankservice.model.User;
import com.banking.bankservice.repository.UserRepository;
import com.banking.bankservice.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(ObjectId id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User " + id + " does not exist"));
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhoneNumber(phoneNumber).orElseThrow(() ->
                new EntityNotFoundException("No user found by phone number " + phoneNumber));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}

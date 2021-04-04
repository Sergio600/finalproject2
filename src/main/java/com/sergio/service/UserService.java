package com.sergio.service;

import com.sergio.converter.UserConverter;
import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getCurrentUser(String email) {
        if (email == null || email == "") {
            throw new InvalidArgumentException("Name can't be null!");
        }

        User user = userRepository.getByEmail(email);

        return user;

    }
}

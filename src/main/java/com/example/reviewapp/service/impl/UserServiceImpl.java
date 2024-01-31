package com.example.reviewapp.service.impl;

import com.example.reviewapp.model.User;
import com.example.reviewapp.repository.UserRepository;
import com.example.reviewapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

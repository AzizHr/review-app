package com.example.reviewapp.service.impl;

import com.example.reviewapp.model.User;
import com.example.reviewapp.repository.UserRepository;
import com.example.reviewapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

package com.example.reviewapp.service.impl;

import com.example.reviewapp.dto.RegisterRequest;
import com.example.reviewapp.model.User;
import com.example.reviewapp.repository.UserRepository;
import com.example.reviewapp.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public User register(RegisterRequest registerRequest) {

        User user = modelMapper.map(registerRequest, User.class);

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.save(user);

    }
}

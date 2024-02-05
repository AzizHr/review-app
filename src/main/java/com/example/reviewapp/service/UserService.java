package com.example.reviewapp.service;

import com.example.reviewapp.dto.RegisterRequest;
import com.example.reviewapp.model.User;

public interface UserService {
    User register(RegisterRequest registerRequest);
}

package com.example.reviewapp.service;

import com.example.reviewapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

}

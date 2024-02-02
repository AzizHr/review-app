package com.example.reviewapp;

import com.example.reviewapp.inums.Role;
import com.example.reviewapp.model.User;
import com.example.reviewapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewAppApplication {

    private static UserRepository userRepository = null;

    @Autowired
    public ReviewAppApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReviewAppApplication.class, args);
        User user = new User();
        user.setFirstname("Aziz");
        user.setLastname("Harkati");
        user.setEmail("aziz@gmail.com");
        user.setPassword("12345");
        user.setRole(Role.USER);

        userRepository.save(user);

    }

}

package com.example.reviewapp.controller;

import com.example.reviewapp.dto.RegisterRequest;
import com.example.reviewapp.inums.Role;
import com.example.reviewapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping
    public String index(Model model) {
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setFirstname("Ahmed");
//        registerRequest.setLastname("Kamal");
//        registerRequest.setEmail("ahmed@gmail.com");
//        registerRequest.setPassword("12346");
//        registerRequest.setRole(Role.ADMINISTRATOR);
//        userService.register(registerRequest);
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setFirstname("Amine");
//        registerRequest1.setLastname("Mohammed");
//        registerRequest1.setEmail("amine@gmail.com");
//        registerRequest1.setPassword("12347");
//        registerRequest1.setRole(Role.MODERATOR);
//        userService.register(registerRequest1);
//        RegisterRequest registerRequest2 = new RegisterRequest();
//        registerRequest2.setFirstname("Aziz");
//        registerRequest2.setLastname("Harkati");
//        registerRequest2.setEmail("aziz@gmail.com");
//        registerRequest2.setPassword("12345");
//        registerRequest2.setRole(Role.USER);
//        userService.register(registerRequest2);
        return "index";
    }

}

package com.example.reviewapp.dto;

import com.example.reviewapp.inums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}

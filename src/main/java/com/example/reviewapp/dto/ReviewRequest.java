package com.example.reviewapp.dto;

import com.example.reviewapp.inums.Reaction;
import com.example.reviewapp.model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private UUID id;
    private LocalDate date;
    private String title;
    private String message;
    private Reaction reaction;
    private long userId;

}

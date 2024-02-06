package com.example.reviewapp.dto;

import com.example.reviewapp.inums.ReactionType;
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
    private String title;
    private String message;
    private long userId;

}

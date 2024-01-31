package com.example.reviewapp.model;

import com.example.reviewapp.inums.Reaction;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue()
    private UUID id;
    private LocalDate date;
    private String title;
    private String message;
    private Reaction reaction;
    @ManyToOne
    private User user;

}

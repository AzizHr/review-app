package com.example.reviewapp.model;

import com.example.reviewapp.inums.ReactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private ReactionType reaction;
    @ManyToOne
    private Review review;
    @ManyToOne
    private User user;

}

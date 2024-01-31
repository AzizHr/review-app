package com.example.reviewapp.service;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ReviewService {

    Review save(ReviewRequest reviewRequest);
    Review update(ReviewRequest reviewRequest);
    boolean delete(UUID id);
    List<Review> findAll();
    Review findById(UUID id);

}

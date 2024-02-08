package com.example.reviewapp.service;

import com.example.reviewapp.dto.ReactionRequest;
import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Reaction;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.repository.ReactionRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    Review save(ReviewRequest reviewRequest) throws Exception;
    Review update(ReviewRequest review) throws Exception;
    void delete(UUID id) throws ReviewNotFoundException;
    List<Review> findAll();
    List<Review> findAllReportedReviews();
    Reaction like(ReactionRequest reaction);
    Review findById(UUID id) throws Exception;
    Review report(UUID id) throws ReviewNotFoundException;

}

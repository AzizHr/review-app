package com.example.reviewapp.service;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ReviewService {

    Review save(ReviewRequest reviewRequest) throws Exception;
    Review update(ReviewRequest reviewRequest) throws Exception;
    void delete(UUID id) throws ReviewNotFoundException;
    List<Review> findAll();
    Review findById(UUID id) throws Exception;

}

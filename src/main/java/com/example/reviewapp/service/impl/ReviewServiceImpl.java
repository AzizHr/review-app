package com.example.reviewapp.service.impl;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.repository.ReviewRepository;
import com.example.reviewapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(ReviewRequest reviewRequest) {
        return null;
    }

    @Override
    public Review update(ReviewRequest reviewRequest) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public Review findById(UUID id) {
        return null;
    }
}

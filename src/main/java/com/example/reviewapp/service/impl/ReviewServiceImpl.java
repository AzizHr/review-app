package com.example.reviewapp.service.impl;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.repository.ReviewRepository;
import com.example.reviewapp.repository.UserRepository;
import com.example.reviewapp.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Review save(ReviewRequest reviewRequest) throws Exception {
        Review review = modelMapper.map(reviewRequest, Review.class);
        review.setUser(userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new Exception("No user found")));
        return reviewRepository.save(review);
    }

    @Override
    public Review update(ReviewRequest reviewRequest) throws Exception {
        if(reviewRepository.findById(reviewRequest.getId()).isPresent()) {
            Review review = modelMapper.map(reviewRequest, Review.class);
            review.setUser(userRepository.findById(reviewRequest.getUserId())
                    .orElseThrow(() -> new Exception("No user found")));
            return reviewRepository.save(review);
        }
        throw new ReviewNotFoundException("No review was found");
    }

    @Override
    public void delete(UUID id) throws ReviewNotFoundException {
        if(reviewRepository.findById(id).isPresent()) {
            reviewRepository.deleteById(id);
        }
        throw new ReviewNotFoundException("No review was found");
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(UUID id) throws ReviewNotFoundException {
        if(reviewRepository.findById(id).isPresent()) {
            return reviewRepository.findById(id).get();
        }
        throw new ReviewNotFoundException("No review was found");
    }
}

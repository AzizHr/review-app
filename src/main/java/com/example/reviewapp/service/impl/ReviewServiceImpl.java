package com.example.reviewapp.service.impl;

import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.repository.ReviewRepository;
import com.example.reviewapp.repository.UserRepository;
import com.example.reviewapp.service.ReviewService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Review save(ReviewRequest reviewRequest) throws Exception {
        Review review = modelMapper.map(reviewRequest, Review.class);
        review.setDate(LocalDate.now());
        review.setReported(false);
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
    public List<Review> findAllReportedReviews() {
        return reviewRepository.findAllReportedReviews();
    }

    @Override
    public Review findById(UUID id) throws ReviewNotFoundException {
        if(reviewRepository.findById(id).isPresent()) {
            return reviewRepository.findById(id).get();
        }
        throw new ReviewNotFoundException("No review was found");
    }

    @Override
    public Review report(UUID id) throws ReviewNotFoundException {
        if(reviewRepository.findById(id).isPresent()) {
            Review review = reviewRepository.findById(id).get();
            review.setReported(true);
            return reviewRepository.save(review);
        }
        throw new ReviewNotFoundException("No review was found");
    }
}

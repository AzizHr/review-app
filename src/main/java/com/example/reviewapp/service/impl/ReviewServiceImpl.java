package com.example.reviewapp.service.impl;

import com.example.reviewapp.dto.ReactionRequest;
import com.example.reviewapp.dto.ReviewRequest;
import com.example.reviewapp.exception.ReviewNotFoundException;
import com.example.reviewapp.model.Reaction;
import com.example.reviewapp.model.Review;
import com.example.reviewapp.repository.ReactionRepository;
import com.example.reviewapp.repository.ReviewRepository;
import com.example.reviewapp.repository.UserRepository;
import com.example.reviewapp.service.ReviewService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReactionRepository reactionRepository;
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
    public Review update(Review review) throws Exception {
        if(reviewRepository.findById(review.getId()).isPresent()) {
            return reviewRepository.save(review);
        }
        throw new ReviewNotFoundException("No review was found");
    }


    @Override
    public Reaction like(ReactionRequest reactionRequest) {
        Reaction reaction = modelMapper.map(reactionRequest, Reaction.class);

        // Check if a similar reaction already exists in the database
        Optional<Reaction> existingReaction = reactionRepository.findByUserIdAndReviewId(reaction.getUser().getId(), reaction.getReview().getId());

        if (existingReaction.isPresent()) {
            // Similar reaction already exists, you can handle this case or do nothing
            // For example, you might throw an exception, log a message, or return the existing reaction
            return existingReaction.get();
        } else {
            // Save the new reaction since it doesn't exist in the database
            return reactionRepository.save(reaction);
        }
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

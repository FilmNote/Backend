package com.rest.filmnote.service;

import com.rest.filmnote.domain.Review;
import com.rest.filmnote.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> findAllByMovieId(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId);
    }

    public Review findById(Long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    public boolean checkPassword(Long reviewId, int password) {
        Review review = reviewRepository.findById(Math.toIntExact(reviewId)).orElse(null);
        if (review != null) {
            return Objects.equals(review.getPassword(), password);
        }
        return false;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(Math.toIntExact(reviewId));
    }

    public void updateReview(Long reviewId, Review updatedReview) {
        reviewRepository.findById(Math.toIntExact(reviewId))
                .map(review -> {
                    review.setTitle(updatedReview.getTitle());
                    review.setContent(updatedReview.getContent());
                    review.setUpdatedAt(LocalDateTime.now());
                    return reviewRepository.save(review);
                });
    }

}

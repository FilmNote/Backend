package com.rest.filmnote.controller;

import com.rest.filmnote.domain.Review;
import com.rest.filmnote.domain.ReviewDTO;
import com.rest.filmnote.repository.ReviewRepository;
import com.rest.filmnote.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping("/write/{id}")
    public Review writeReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setMovieId(reviewDTO.getMovieId());
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        review.setPassword(reviewDTO.getPassword());

        return reviewRepository.save(review);
    }

    @GetMapping("/list/{id}")
    public List<Review> getAllReviewByMovieId(@PathVariable Long id) {
        return reviewService.findAllByMovieId(id);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, @RequestBody Map<String, String> requestBody) {
        String password = requestBody.get("password");

        if (reviewService.checkPassword(reviewId, Integer.parseInt(password))) {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok("리뷰가 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
        }
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewDTO reviewDTO) {

        int password = reviewDTO.getPassword();

        if (reviewService.checkPassword(reviewId, password)) {
            Review updatedReview = new Review();
            updatedReview.setTitle(reviewDTO.getTitle());
            updatedReview.setContent(reviewDTO.getContent());
            updatedReview.setUpdatedAt(LocalDateTime.now());

            reviewService.updateReview(reviewId, updatedReview);
            return ResponseEntity.ok("리뷰가 수정되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
        }
    }

}

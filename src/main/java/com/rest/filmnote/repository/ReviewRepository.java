package com.rest.filmnote.repository;

import com.rest.filmnote.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByMovieId(Long movieId);

    Review findByReviewId(Long reviewId);
}
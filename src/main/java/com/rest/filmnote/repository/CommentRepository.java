package com.rest.filmnote.repository;

import com.rest.filmnote.domain.Comment;
import com.rest.filmnote.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByReviewId(int reviewId);
}
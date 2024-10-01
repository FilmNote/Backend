package com.rest.filmnote.controller;

import com.rest.filmnote.domain.Comment;
import com.rest.filmnote.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentRepository commentRepository;

    @GetMapping("/{reviewId}")
    public List<Comment> getCommentsByReviewId(@PathVariable int reviewId) {
        return commentRepository.findByReviewId(reviewId);
    }

    @PostMapping("/{reviewId}")
    public Comment createComment(@RequestBody Comment comment, @PathVariable int reviewId) {
        comment.setCreateDate(LocalDateTime.now());
        comment.setReviewId(reviewId);
        return commentRepository.save(comment);
    }
}
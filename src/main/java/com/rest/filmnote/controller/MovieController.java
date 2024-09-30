package com.rest.filmnote.controller;

import com.rest.filmnote.domain.Movie;
import com.rest.filmnote.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    /**
     * Movie DB 저장
     */
    @PostMapping("/popular")
    public ResponseEntity<String> getPopularMovies() {
        return ResponseEntity.ok(movieService.insertMovie());
    }

    /**
     * Movie 전체 조회
     */
    @GetMapping()
    public ResponseEntity<List<Movie>> getMovieAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    /**
     * Movie 상세 조회
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String keyword) {
        List<Movie> movies = movieService.searchMovies(keyword);
        return ResponseEntity.ok(movies);
    }
}
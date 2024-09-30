package com.rest.filmnote.repository;

import com.rest.filmnote.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM movie WHERE title LIKE %:keyword% OR overview LIKE %:keyword%", nativeQuery = true)
    List<Movie> searchByKeyword(@Param("keyword") String keyword);
}
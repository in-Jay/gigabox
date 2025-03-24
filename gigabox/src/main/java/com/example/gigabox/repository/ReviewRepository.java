package com.example.gigabox.repository;

import com.example.gigabox.dto.Movie;
import com.example.gigabox.dto.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByMovie(Movie movie);

    Page<Review> findByMovie(Movie movie, Pageable pageable);
}

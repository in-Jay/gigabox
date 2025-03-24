package com.example.gigabox.repository;

import com.example.gigabox.dto.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(Long id);

    Optional<Movie> findByMovieCode(String movieCode);

    @Query("SELECT m FROM Movie m ORDER BY CAST(m.ranking AS integer) ASC")
    List<Movie> findTopMovies(Pageable pageable);

    // 제목에 특정 단어가 포함된 영화 찾기
    List<Movie> findByTitleContainingIgnoreCase(String title, Sort sort);

}


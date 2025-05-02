package com.example.gigabox.repository;

import com.example.gigabox.dto.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(Long id);

    Optional<Movie> findByMovieCode(String movieCode);

    @Query("SELECT m FROM Movie m ORDER BY CASE WHEN m.ranking IS NULL THEN 1 ELSE 0 END ASC, CAST(m.ranking AS integer) ASC")
    List<Movie> findTopMovies(Pageable pageable);

    // 제목에 특정 단어가 포함된 영화 찾기
    @Query(value = """
    SELECT * FROM movie
    WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%'))
    ORDER BY ranking IS NULL, CAST(ranking AS UNSIGNED)
    """, nativeQuery = true)
    List<Movie> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Transactional
    @Modifying
    @Query("UPDATE Movie m SET m.ranking = null")
    void resetAllRankings();

    @Transactional
    @Modifying
    @Query("UPDATE Movie m SET m.rate = '0%'")
    void resetAllRates();

}


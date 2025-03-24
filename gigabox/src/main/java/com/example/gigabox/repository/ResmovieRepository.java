package com.example.gigabox.repository;

import com.example.gigabox.dto.Resmovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface ResmovieRepository extends JpaRepository<Resmovie, Long> {
    Optional<Resmovie> findByTitle(String title);  // 제목으로 영화 찾기

    List<Resmovie> findByStartDateBeforeAndEndDateAfter(LocalDate startdate, LocalDate endDate);

    Optional<Resmovie> findByMovieCode(String movieCode);
}

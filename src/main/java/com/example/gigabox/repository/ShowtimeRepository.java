package com.example.gigabox.repository;

import com.example.gigabox.dto.Resmovie;
import com.example.gigabox.dto.Restheater;
import com.example.gigabox.dto.Showtime;
import com.example.gigabox.dto.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowtimeRepository extends JpaRepository <Showtime, Long> {
    // 특정 영화와 극장에 대해 이미 상영시간이 존재하는지 확인
    boolean existsByResmovieAndRestheater(Resmovie resmovie, Restheater restheater);


    @Query("SELECT s FROM Showtime s WHERE s.resmovie.id = :resmovieId AND s.restheater.id = :restheaterId")
    List<Showtime> findByResmovieIdAndRestheaterId(@Param("resmovieId") Long resmovieId, @Param("restheaterId") Long restheaterId);


    @Query("SELECT DISTINCT s.restheater FROM Showtime s WHERE s.resmovie.id = :resmovieId")
    List<Restheater> getRestheatersByResmovie(@Param("resmovieId") Long resmovieId);


    List<Showtime> findByRestheaterId(Long restheaterId);


}

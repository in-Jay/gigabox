package com.example.gigabox.service;

import com.example.gigabox.dto.*;
import com.example.gigabox.repository.ResmovieRepository;
import com.example.gigabox.repository.RestheaterRepository;
import com.example.gigabox.repository.ShowtimeRepository;
import com.example.gigabox.repository.TheaterRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShowtimeService {

    @Autowired
    private ResmovieRepository resmovieRepository;

    @Autowired
    private RestheaterRepository restheaterRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    // 애플리케이션 시작 시 상영 시간 미리 생성
    @PostConstruct
    public void init() {
        createShowTimesForAllMoviesAndRestheaters();
    }


    @Transactional
    public void createShowTimesForAllMoviesAndRestheaters() {
        List<Resmovie> resmovies = resmovieRepository.findAll(); // 모든 영화 가져오기
        List<Restheater> restheaters = restheaterRepository.findAll(); // 모든 극장 가져오기

        // 모든 영화와 극장의 조합에 대해 상영시간 생성
        for (Resmovie resmovie : resmovies) {
            for (Restheater restheater : restheaters) {
                // 이미 상영시간이 생성되어 있지 않으면 상영시간을 생성
                if (!showtimeRepository.existsByResmovieAndRestheater(resmovie, restheater)) {
                    createShowTimesFormovieAndRestheater(resmovie.getId(), restheater.getId());
                }
            }
        }
    }

    @Transactional
    public void createShowTimesFormovieAndRestheater(Long resmovieId, Long restheaterId) {
        Resmovie resmovie = resmovieRepository.findById(resmovieId).orElseThrow(() -> new RuntimeException("영화가 존재하지 않습니다."));
        Restheater restheater = restheaterRepository.findById(restheaterId).orElseThrow(() -> new RuntimeException("극장이 존재하지 않습니다."));

        System.out.println("영화: " + resmovie.getTitle() + " / 극장: " + restheater.getTheater());  // 로그 추가

        // 상영시간이 이미 생성된 영화와 극장 조합인지 확인
        if (showtimeRepository.existsByResmovieAndRestheater(resmovie, restheater)) {
            return; // 이미 상영시간이 있으며 추가하지 않음
        }

        // 시작날짜부터 종료날짜까지 상영시간 생성
        LocalDateTime startDate = resmovie.getStartDate().atStartOfDay();
        LocalDateTime endDate = resmovie.getEndDate().atTime(23, 59);

        // 상영 시간 목록 (매일 동일한 시간)
        LocalTime[] showTimes = {
                LocalTime.of(7, 0),    // 첫 번째 상영 시간: 7:00
                LocalTime.of(10, 20),  // 두 번째 상영 시간: 10:20
                LocalTime.of(13, 40),  // 세 번째 상영 시간: 13:40
                LocalTime.of(17, 0),   // 네 번째 상영 시간: 17:00
                LocalTime.of(20, 20)   // 다섯 번째 상영 시간: 20:20
        };

        // 각 날짜에 대해 상영 시간 생성
        LocalDateTime showtime;
        for (LocalDate currentDate = startDate.toLocalDate(); !currentDate.isAfter(endDate.toLocalDate()); currentDate = currentDate.plusDays(1)) {
            for (LocalTime time : showTimes) {
                showtime = currentDate.atTime(time); // 해당 날짜의 상영 시간 설정

                // 상영 시간 생성
                Showtime newShowtime = new Showtime();
                newShowtime.setShowTime(showtime);
                newShowtime.setResmovie(resmovie);
                newShowtime.setRestheater(restheater);
                showtimeRepository.save(newShowtime);

                System.out.println("상영 시간 생성: " + showtime);
            }
        }
    }



public List<ShowtimeDTO> getShowTimesByResmovieAndRestheater(Long resmovieId, Long restheaterId) {
    List<Showtime> showtimes = showtimeRepository.findByResmovieIdAndRestheaterId(resmovieId, restheaterId);

    // Showtime 객체를 ShowtimeDTO로 변환
    return showtimes.stream()
            .map(showtime -> new ShowtimeDTO(
                    showtime.getId(),
                    showtime.getShowTime(),
                    showtime.getResmovie().getTitle(), // 영화 제목
                    showtime.getRestheater().getTheater() // 극장 이름
            ))
            .collect(Collectors.toList());
}
}
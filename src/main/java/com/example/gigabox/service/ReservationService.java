package com.example.gigabox.service;

import com.example.gigabox.dto.*;
import com.example.gigabox.repository.ReservationSeatRepository;
import com.example.gigabox.repository.ResmovieRepository;
import com.example.gigabox.repository.ReservationRepository;
import com.example.gigabox.repository.ShowtimeRepository;
import com.example.gigabox.users.GigaUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ResmovieRepository resmovieRepository;
    private final ReservationRepository reservationRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private static final String reservationPage = "http://www.cgv.co.kr/movies/?lt=1&ft=0";

    public List<Resmovie> getMovies() throws IOException {
        List<Resmovie> resmovies = new ArrayList<>();
        LocalDate today = LocalDate.now();

        try {
            Document doc = Jsoup.connect(reservationPage).get();
            Elements movieEle = doc.select(".sect-movie-chart ol li");
            int maxMovies = 2;
            int count = 0;

            for (Element element: movieEle) {
                if (count >= maxMovies) {
                    break;
                }

                String title = element.select(".title").text();
                String age = element.select("i.cgvIcon.etc").text();
                String posterUrl = element.select("img").attr("src");
                String date = element.select(".txt-info strong").text();
                Element ddayElement = element.select(".txt-info .dday").first();
                Element releaseElement = element.select(".txt-info span").first();
                if (ddayElement != null) {
                    ddayElement.remove();
                }
                if (releaseElement != null) {
                    releaseElement.remove();
                }
                date = element.select(".txt-info strong").text().trim();


                String detailUrl = "http://www.cgv.co.kr/movies/detail-view/?midx=" + element.select("a").attr("href").split("=")[1];
                String movieCode = extractMovieCode(detailUrl);


                Resmovie resmovie = new Resmovie(title, age, posterUrl, date, movieCode);
                resmovies.add(resmovie);


                count++;
            }

            if (!resmovies.isEmpty()) {
                saveAllResmovies(resmovies);  // 저장 메서드 호출
            }

            resmovieRepository.saveAll(resmovies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resmovies;
    }

    // detailUrl에서 movieCode 추출하는 메서드
    private String extractMovieCode(String detailUrl) {
        String[] parts = detailUrl.split("midx=");
        if (parts.length > 1) {
            return parts[1];
        }
        return "";  // movieCode를 찾을 수 없으면 빈 문자열 반환
    }

    //saveAllResMovies
    private void saveAllResmovies(List<Resmovie> resmovies) {
        for(Resmovie resmovie : resmovies){
            Optional<Resmovie> existResmovie = resmovieRepository.findByMovieCode(resmovie.getMovieCode());

            if(existResmovie.isPresent()){
                Resmovie exist = existResmovie.get();
                exist.setTitle(resmovie.getTitle());
                exist.setAge(resmovie.getAge());
                exist.setPosterUrl(resmovie.getPosterUrl());
                exist.setDate(resmovie.getDate());
                exist.setMovieCode(resmovie.getMovieCode());

                resmovieRepository.save(exist);
            }else {
                resmovieRepository.save(resmovie);
            }
        }


    }

    // 이 위에까지는 영화 목록 불러오는 것


    //선택날짜에 상영하는 영화를 불러오는 메서드
    public List<Resmovie> getResmoviesByDateRange(LocalDate selectedDate) {
        // 선택한 날짜가 startDate 이후이고 endDate 이전인 영화들만 조회
        LocalDate startOfDay = selectedDate.atStartOfDay().toLocalDate();  // 자정 기준으로 설정
        LocalDate endOfDay = selectedDate.atTime(23, 59, 59).toLocalDate(); // 해당 날짜의 마지막 시간
        return resmovieRepository.findByStartDateBeforeAndEndDateAfter(startOfDay, endOfDay);
    }

    //특정 영화와 극장의 상영시간을 반환
    public List<Showtime> getShowTimesByResmovieAndRestheater(Long resmovieId, Long restheaterId) {
        return showtimeRepository.findByResmovieIdAndRestheaterId(resmovieId, restheaterId);
    }


    @Transactional
    public Reservation saveReservation(String date, String resmovie, String area, String restheater, String time) {
        Reservation reservation = new Reservation();
        reservation.setDate(date);
        reservation.setResmovie(resmovie);
        reservation.setArea(area);
        reservation.setRestheater(restheater);
        reservation.setTime(time);

        // 예약 정보 데이터베이스에 저장
        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation;
    }

    public List<Restheater> getRestheatersByResmovie(Long resmovieId) {
        return showtimeRepository.getRestheatersByResmovie(resmovieId);
    }


    // 특정 극장에서 상영 중인 영화의 상영 시간 목록을 반환
    public List<Showtime> getShowTimesByRestheater(Long restheaterId) {
        return showtimeRepository.findByRestheaterId(restheaterId);
    }


    // 예약 정보 저장
    public Reservation saveReservation(Reservation reservation) {

        return reservationRepository.save(reservation);
    }



    @Transactional
    public boolean saveSeats(Long reservationId, List<String> seats, int adultnum, int youthnum) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        List<ReservationSeat> reservationSeats = seats.stream()
                .map(seat -> new ReservationSeat(reservation, seat, adultnum, youthnum)) // ReservationSeat 객체로 변환
                .collect(Collectors.toList());

        // Reservation의 seats 리스트에 예약된 좌석을 추가
        reservation.getSeats().addAll(reservationSeats);  // 기존에 있던 seats 리스트에 추가

        // 모든 좌석 저장
        reservationSeatRepository.saveAll(reservationSeats);

        return true;
    }


    // 예약 ID로 해당 예약의 좌석 정보 조회
    public List<String> getReservedSeats(Long reservationId) {
        // 예약 ID로 해당 예약을 찾기
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("예약을 찾을 수 없습니다."));

        // 해당 예약에 포함된 좌석 정보 반환 (ReservationSeat 객체에서 seat 정보를 추출)
        return reservation.getSeats().stream()
                .map(ReservationSeat::getSeat)  // ReservationSeat에서 seat 정보만 추출
                .toList();
    }


    // 예약된 좌석 조회
    public List<String> getReservedSeats(String date, String resmovie, String restheater) {
        List<Reservation> reservations = reservationRepository.findByDateAndResmovieAndRestheater(date, resmovie, restheater);

        // 모든 예약에서 좌석 정보 추출
        return reservations.stream()
                .flatMap(reservation -> reservation.getSeats().stream())  // 모든 예약에서 좌석을 가져옴
                .map(ReservationSeat::getSeat)  // ReservationSeat 객체에서 seat 정보만 추출
                .toList();
    }



    // 사용자별 예약 내역과 영화 이미지를 가져오는 메서드
    public List<Reservation> getReservationsByUser(GigaUser user) {
        List<Reservation> reservations = reservationRepository.findByUserId(user.getId());

        // 각 예약에 대해 resmovieId를 기반으로 영화 정보 설정
        for (Reservation reservation : reservations) {
            Long resmovieId = reservation.getResmovieId();
            Resmovie resmovie = resmovieRepository.findById(resmovieId).orElse(null);
            if (resmovie != null) {
                reservation.setResmovieImage(resmovie.getPosterUrl());  // 영화 이미지 URL을 설정
            }
        }
        return reservations;
    }


    //최근 게시글 4개 가져오기
    public List<Reservation> getPosts() {
        List<Reservation> reservations = reservationRepository.findAll(Sort.by(Sort.Order.desc("id")));
        return reservations.stream().limit(4).collect(Collectors.toList());
    }
}

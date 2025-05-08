package com.example.gigabox.repository;

import com.example.gigabox.dto.Reservation;
import com.example.gigabox.dto.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationSeatRepository extends JpaRepository <ReservationSeat, Long> {
    // 날짜, 영화, 극장, 시간 기준으로 예약된 좌석을 가져오는 쿼리 메서드 추가
    @Query("SELECT rs FROM ReservationSeat rs " +
            "WHERE rs.reservation.date = :date " +
            "AND rs.reservation.resmovie = :resmovie " +
            "AND rs.reservation.restheater = :restheater " +
            "AND rs.reservation.time = :time")
    List<ReservationSeat> findReservedSeats(@Param("date") String date,
                                            @Param("resmovie") String resmovie,
                                            @Param("restheater") String restheater,
                                            @Param("time") String time
                                            );
}

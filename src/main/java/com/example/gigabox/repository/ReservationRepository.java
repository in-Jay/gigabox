package com.example.gigabox.repository;

import com.example.gigabox.dto.Reservation;
import com.example.gigabox.users.GigaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 날짜, 영화, 극장에 해당하는 예약을 조회하는 메서드
    @Query("SELECT r FROM Reservation r WHERE r.date = :date AND r.resmovie = :resmovie AND r.restheater = :restheater")
    List<Reservation> findByDateAndResmovieAndRestheater(@Param("date") String date,
                                                         @Param("resmovie") String resmovie,
                                                         @Param("restheater") String restheater);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findByUserId(@Param("userId") Long userId);


}

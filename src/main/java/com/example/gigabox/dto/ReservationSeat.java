package com.example.gigabox.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Setter
@Getter
public class ReservationSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 좌석 ID (Primary Key)

    private String seat;  // 좌석 번호 (예: "A1", "B2", "C3")

    @Column(name = "adultnum")
    private int adultnum;

    @Column(name = "youthnum")
    private int youthnum;
    // 해당 좌석이 속한 예약 (ManyToOne 관계)
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;  // 예약 정보 (Foreign Key)

    // 기본 생성자
    public ReservationSeat() {}

    // ReservationSeat 생성자
    public ReservationSeat(Reservation reservation, String seat, int adultnum, int youthnum) {
        this.reservation = reservation;
        this.seat = seat;
        this.adultnum = adultnum;
        this.youthnum = youthnum;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

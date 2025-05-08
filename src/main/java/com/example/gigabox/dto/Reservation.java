package com.example.gigabox.dto;

import com.example.gigabox.users.GigaUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String resmovie;
    private Long resmovieId;
    private String area;
    private String restheater;
    private Long restheaterId;
    private String time;
    private String resmovieImage;
    private int count;
    // 총 금액
    private Integer totalPrice;

    // 예약에 해당하는 좌석들을 저장하는 컬렉션
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationSeat> seats;   // 좌석 정보 (OneToMany 관계)

    // 사용자 정보 (GigaUser와의 관계)
    @ManyToOne(fetch = FetchType.LAZY)  // GigaUser와 다대일 관계
    @JoinColumn(name = "user_id")  // 외래 키를 지정
    private GigaUser user;


    // 예약 생성자
    public Reservation(String date, String resmovie, String area, String restheater, String time, GigaUser user, String resmovieImage, Integer totalPrice) {
        this.date = date;
        this.resmovie = resmovie;
        this.area = area;
        this.restheater = restheater;
        this.time = time;
        this.user = user;
        this.resmovieImage=resmovieImage;
        this.totalPrice=totalPrice;
    }

}

package com.example.gigabox.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Resmovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String age;
    private String posterUrl;
    private String date;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(unique = true)
    private String movieCode;

    @OneToMany(mappedBy  = "resmovie")
    private List<Showtime> showtime;   //showtime은 여러개라 list임

    public Resmovie(String title, String age, String posterUrl, String date, String movieCode) {
        this.title = title;
        this.age = age;
        this.posterUrl = posterUrl;
        this.date = date;
        this.movieCode = movieCode;

        setStartDateFromDateString();
        setEndDateFromStartDate();
    }

    public void setStartDateFromDateString() {
        if (date != null && !date.isEmpty()) {
            // 날짜 포맷을 맞춰서 LocalDate로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd"); // 날짜 형식
            this.startDate = LocalDate.parse(date, formatter); // date를 startDate로 변환
        }
    }

    // startDate의 1달 뒤를 endDate로 설정
    public void setEndDateFromStartDate() {
        if (this.startDate != null) {
            this.endDate = this.startDate.plusMonths(1); // startDate로부터 1달 뒤를 endDate로 설정
        }
    }
}

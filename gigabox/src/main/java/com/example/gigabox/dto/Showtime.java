package com.example.gigabox.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "showtime")
public class Showtime {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime showTime;

    @ManyToOne  //상영시간은 여러개인데 영화는 1개
    @JoinColumn(name = "resmovie_id" , nullable = false)
    private Resmovie resmovie;

    @ManyToOne
    @JoinColumn(name = "restheater_id", nullable = false)
    private Restheater restheater;


    // @JsonProperty를 사용하여 JSON 직렬화 시 원하는 형식으로 반환할 수 있음
    @JsonProperty("showTime")
    @Transient
    public String getFormattedShowTime() {
        return showTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


}

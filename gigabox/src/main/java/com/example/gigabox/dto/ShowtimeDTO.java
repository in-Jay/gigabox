package com.example.gigabox.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowtimeDTO {
    private Long id;
    private LocalDateTime showTime;
    private String movieTitle;
    private String theaterName;
}

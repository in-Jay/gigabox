package com.example.gigabox.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100)
    private String review;
    private LocalDateTime createDate;

    @ManyToOne
    private Movie movie;

    private LocalDateTime modifyDate;
}

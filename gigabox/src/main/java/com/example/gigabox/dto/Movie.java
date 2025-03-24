package com.example.gigabox.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String posterUrl;
    private String ranking;
    private String title;
    private String rate;
    private String date;
    private String age;
    @Column(columnDefinition = "TEXT")
    private String story;
    private String detailUrl;
    private String dTitle;
    private String director;
    private String actors;
    private String genre;
    private String dInfo;
    private double gpa;
    @Column(unique = true)
    private String movieCode;
    @ColumnDefault("0")
    private int likes;

    public Movie (String posterUrl, String ranking, String title, String rate, String date, String age, String story, String detailUrl, String dTitle, String director, String actors, String genre, String dInfo, double gpa, String movieCode) {
        this.posterUrl = posterUrl;
        this.ranking = ranking;
        this.title = title;
        this.rate = rate;
        this.date = date;
        this.age = age;
        this.story = story;
        this.detailUrl = detailUrl;
        this.dTitle = dTitle;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.dInfo = dInfo;
        this.gpa = gpa;
        this.movieCode = movieCode;
    }


    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    private List<Review> reviews;



}







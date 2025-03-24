package com.example.gigabox.controller;

import com.example.gigabox.dto.Movie;
import com.example.gigabox.dto.Review;
import com.example.gigabox.service.MovieService;
import com.example.gigabox.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/movie")
public class MovieController {
    public final MovieService movieService;
    public final ReviewService reviewService;

    @GetMapping
    public String showMovies(Model model, @RequestParam(value = "search", required = false) String search) {
        try {
            List<Movie> movies = movieService.getMovies();
            int movieCount;

            if (search != null && !search.isEmpty()) {
                // 제목에 포함된 검색어로 영화 목록을 검색
                movies = movieService.searchMoviesByTitle(search);
            } else {
                // 검색어가 없으면 전체 영화 목록을 반환
                movies = movieService.getMovies();
            }

            movieCount = movies.size();

            model.addAttribute("movies", movies);
            model.addAttribute("movieCount", movieCount);
            model.addAttribute("search", search);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "movie";
    }

    @GetMapping("/detail/{movieCode}")
    public String detailMovie(Model model, @PathVariable("movieCode") String movieCode) {
        Movie detail = this.movieService.getDetail(movieCode);
        long reviewCount = reviewService.getReviewCountByMovie(detail);

        model.addAttribute("detail", detail);
        model.addAttribute("reviewCount", reviewCount);
        return "movie_detail";
    }


    @PostMapping("/detail/{movieCode}")
    public String addReview(@PathVariable("movieCode") String movieCode,
                            @RequestParam(value = "review") String review,
                            Model model) {
        // 영화 정보를 가져옴
        Movie movie = this.movieService.getDetail(movieCode);

        // 리뷰를 저장
        this.reviewService.saveReview(movie, review, LocalDateTime.now());

        // 리뷰 목록을 최신순으로 가져오기 (페이징 처리가 있다면 적절히 페이지 번호와 크기를 조정)
        Page<Review> reviews = this.reviewService.getReviewsByMovie(movie, 0, 20);  // 0번째 페이지, 20개 리뷰 가져오기
        model.addAttribute("reviews", reviews);  // 모델에 리뷰 추가

        // 영화 정보 추가 (영화 상세 정보 표시용)
        model.addAttribute("movie", movie);

        // 리디렉션 후 영화 상세 페이지로 이동
        return String.format("redirect:/movie/detail/%s", movieCode);
    }



}

package com.example.gigabox.service;

import com.example.gigabox.dto.Movie;
import com.example.gigabox.dto.Review;
import com.example.gigabox.message.DataNotFoundException;
import com.example.gigabox.repository.MovieRepository;
import com.example.gigabox.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void saveReview(Movie movie, String review, LocalDateTime createDate) {
        Review reviews = new Review(); // 리뷰 객체를 데이터베이스에 저장
        reviews.setMovie(movie);
        reviews.setReview(review);
        reviews.setCreateDate(createDate);

        this.reviewRepository.save(reviews);
    }

    public long getReviewCountByMovie(Movie detail) {
        return reviewRepository.countByMovie(detail);
    }

    public Review getReview(Long id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if (review.isPresent()) {
            return review.get();
        } else {
            throw new DataNotFoundException("답변이 없습니다.");
        }
    }

    // 영화별 리뷰를 페이징 처리하여 반환
    public Page<Review> getReviewsByMovie(Movie movie, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("createDate")));  // createDate를 기준으로 내림차순 정렬
        return reviewRepository.findByMovie(movie, pageable); // 페이징된 리뷰 반환
    }

    public void modify(Review reviews, String review ) {
        reviews.setReview(review);
        reviews.setModifyDate(LocalDateTime.now());
        this.reviewRepository.save(reviews);
    }
}

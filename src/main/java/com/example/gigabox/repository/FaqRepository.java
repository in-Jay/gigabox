package com.example.gigabox.repository;

import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByCategory(String category);

    Page<Faq> findAll(Pageable pageable);

    Page<Faq> findByCategory(String category, Pageable pageable);

    long count();

    long countByCategory(String category);
    Page<Faq> findAllByOrderByIdDesc(Pageable pageable);

    Page<Faq> findByQuestionContainingOrAnswerContaining(String subject, String content, Pageable pageable);
}

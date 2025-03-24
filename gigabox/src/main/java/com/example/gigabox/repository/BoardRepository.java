package com.example.gigabox.repository;

import com.example.gigabox.dto.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// JpaRepository for Board entity
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findBySubject(String subject);
    Board findBySubjectAndContent(String subject, String content);

    // Paging functionality: retrieve all boards with pagination
    Page<Board> findAll(Pageable pageable);

    // Searching: search by subject or content
    Page<Board> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);

    Page<Board> findAllByOrderByIdDesc(Pageable pageable);

    Optional<Board> findById(Long id);
}

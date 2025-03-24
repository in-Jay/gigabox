package com.example.gigabox.repository;

import com.example.gigabox.dto.Qna;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface QnaRepository extends JpaRepository<Qna, Long> {


}

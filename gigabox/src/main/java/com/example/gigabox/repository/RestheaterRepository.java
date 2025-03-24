package com.example.gigabox.repository;

import com.example.gigabox.dto.Restheater;
import com.example.gigabox.dto.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestheaterRepository extends JpaRepository<Restheater, Long> {
    List<Restheater> findByArea(String area);
}

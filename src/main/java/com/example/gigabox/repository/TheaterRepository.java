package com.example.gigabox.repository;

import com.example.gigabox.dto.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByArea(String area);

}

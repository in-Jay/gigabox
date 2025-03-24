package com.example.gigabox.repository;

import com.example.gigabox.users.GigaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<GigaUser, Long> {
    Optional<GigaUser> findByUsername(String username);
    boolean existsByUsername (String username);
    Optional<GigaUser> findByEmail(String email);
    Optional<GigaUser> findByUsernameAndEmail(String username, String email);
}

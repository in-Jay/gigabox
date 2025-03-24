package com.example.gigabox.emailcheck;

import com.example.gigabox.users.GigaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<GigaUser, Long> {
    Optional<GigaUser> findByEmail(String email);


    boolean existsByRealnameAndBirthdateAndEmail(String realname, LocalDate birthdate, String emailId);
    boolean existsByEmail(String email);
}

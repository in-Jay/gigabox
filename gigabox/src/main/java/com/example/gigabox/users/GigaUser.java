package com.example.gigabox.users;

import com.example.gigabox.dto.Cart;
import com.example.gigabox.dto.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GigaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String realname;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private LocalDateTime passwordToken;
//    private Collection<? extends GrantedAuthority> authorities;

        // 기존 생성자
    public GigaUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;  // 한 사용자가 여러 개의 예약을 가질 수 있도록 설정

    public boolean updatePasswordToken() {
        LocalDateTime now = LocalDateTime.now();

        passwordToken = now;
        return true;
    }

    // 비밀번호 변경 메서드 추가
    public void setPassword(String password) {
        this.password = password;
    }
}

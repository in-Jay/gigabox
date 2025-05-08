package com.example.gigabox.service;

import com.example.gigabox.dto.Reservation;
import com.example.gigabox.repository.ReservationRepository;
import com.example.gigabox.repository.UserRepository;
import com.example.gigabox.users.GigaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public GigaUser getUser(Long id){
        GigaUser gigaUser = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("일치하는 사용자가 없습니다."));
        return gigaUser;
    }
    public GigaUser getUsername(String username){
        Optional<GigaUser> gigaUser = userRepository.findByUsername(username);
        return gigaUser.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")); // 예외 처리
    }

    public GigaUser create (String username, String password, String realname, LocalDate birthdate, String email) {
        GigaUser user = new GigaUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRealname(realname);
        user.setBirthdate(birthdate);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    public void modify(GigaUser gigaUser) {
        // 변경된 사항을 저장
        userRepository.save(gigaUser);
    }

    public boolean checkUsername(String username){
        return userRepository.existsByUsername(username);
    }

    //아이디 찾기
    public String findUsernameByEmail(String email){
        return userRepository.findByEmail(email).map(GigaUser::getUsername).orElse(null);
    }
    //비밀번호 재설정
    public boolean resetPassword(String username, String email, String newPassword){
        Optional<GigaUser> userOpt = userRepository.findByUsernameAndEmail(username, email);
        if(userOpt.isPresent()){
            GigaUser gigaUser = userOpt.get();
            gigaUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(gigaUser);
            return true;
        }
        return false;
    }

    // 사용자 ID로 예약 내역을 가져오는 메서드
    public List<Reservation> getReservationsByUser(GigaUser user) {
        return reservationRepository.findByUserId(user.getId());
    }

}

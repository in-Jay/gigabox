package com.example.gigabox.security;

import com.example.gigabox.dto.CustomUserDetails;
import com.example.gigabox.repository.UserRepository;
import com.example.gigabox.users.GigaUser;
import com.example.gigabox.users.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
//userDetailsService : 사용자의 정보를 담는 인터페이스
//implements : 인터페이스를 부모객체로 상속받을 때 사용하는 키워드
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<GigaUser> _gigaUser = this.userRepository.findByUsername(username);
        if (_gigaUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        GigaUser gigaUser = _gigaUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 권한 부여
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        // CustomUserDetails 생성 시 realname을 함께 전달
        return new CustomUserDetails(gigaUser);
    }
}


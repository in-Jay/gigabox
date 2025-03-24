package com.example.gigabox.dto;

import com.example.gigabox.users.GigaUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final GigaUser gigaUser;  // GigaUser 객체를 가지고 있어야 함

    // GigaUser를 반환하는 메소드
    public GigaUser getGigaUser() {
        return gigaUser;
    }

    // 생성자
    public CustomUserDetails(GigaUser gigaUser) {
        this.gigaUser = gigaUser;
    }

    // GigaUser 반환
    public String getRealname() {
        return gigaUser.getRealname();  // GigaUser에서 realname을 반환
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;  // 권한은 이 예시에서는 무시
    }

    @Override
    public String getPassword() {
        return gigaUser.getPassword();
    }

    @Override
    public String getUsername() {
        return gigaUser.getUsername();
    }

    // 사용자 ID를 반환하는 메서드 추가
    public Long getId() {
        return gigaUser.getId();  // GigaUser에서 ID를 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

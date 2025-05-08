package com.example.gigabox.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.security.web.savedrequest.RequestCache;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();  // 요청 저장
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();  // 리디렉션 처리

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 사용자가 요청한 URL을 얻어옴
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // 요청이 저장된 경우 해당 URL로 리디렉션, 아니면 기본 URL로 리디렉션
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);  // 리디렉션
        } else {
            redirectStrategy.sendRedirect(request, response, "/");  // 기본 리디렉션 URL (예: 메인 페이지)
        }
    }
}



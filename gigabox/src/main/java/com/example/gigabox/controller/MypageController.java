package com.example.gigabox.controller;

import com.example.gigabox.dto.*;
import com.example.gigabox.service.QnaService;
import com.example.gigabox.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@Controller
public class MypageController {
    private final ReservationService reservationService;
    private final QnaService qnaService;

    @GetMapping("")
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpServletRequest request) {
        // 로그인되지 않은 사용자가 접근하려면 로그인 페이지로 리다이렉트
        if (userDetails == null) {
            //로그인하려는 페이지를 저장(들어가려는 페이지)한 후, 로그인 페이지로 이동
            String currentUrl = request.getRequestURL().toString();
            request.getSession().setAttribute("redirectUrl", currentUrl);
            return "redirect:/user/login";  // 로그인 페이지로 리다이렉트
        }

        // 로그인된 사용자 정보 가져오기
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;

        String username = customUserDetails.getUsername();
        String realname = customUserDetails.getRealname(); // CustomUserDetails에서 realname 가져오기

        model.addAttribute("username", username);
        model.addAttribute("realname", realname);

        List<Reservation> recentReservation = reservationService.getPosts();
        List<Qna> recentQna = qnaService.getPosts();
        model.addAttribute("recentReservation", recentReservation);
        model.addAttribute("recentQna", recentQna);

        return "mypage/home"; // mypage.html로 이동
    }

}

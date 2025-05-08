package com.example.gigabox.controller;

import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.Movie;
import com.example.gigabox.service.BoardService;
import com.example.gigabox.service.MovieService;
import com.example.gigabox.service.UserService;
import com.example.gigabox.users.GigaUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

@SessionAttributes("loggedInUser")
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final BoardService boardService;
    private final MovieService movieService;
    private final UserService userService;

    @GetMapping("/")
    public String Home(Model model, Principal principal) {
        // 영화 목록과 게시글 목록을 모델에 추가
        List<Movie> topMovies = movieService.getHighRank(4);
        List<Board> topBoard = boardService.getPosts(1);

        model.addAttribute("topMovies", topMovies);
        model.addAttribute("topBoard", topBoard);


        // 로그인된 사용자 정보가 있으면 home.html 페이지를 반환
        return "home";
    }


    @PostMapping("/search")
    public String searchMovies (@RequestParam("search") String search) {
        return "redirect:/movie?search" + search;
    }
}

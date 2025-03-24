package com.example.gigabox.controller;

import com.example.gigabox.dto.*;
import com.example.gigabox.service.BoardService;
import com.example.gigabox.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/support")
public class SupportController {
    private final FaqService faqService;
    private final BoardService boardService;

    @GetMapping("/home")
    public String supportHome(Model model) {
        List<Board> noticeBoard = boardService.getList();

        List<Board> recentBoard = boardService.getPosts(4);
        List<Faq> recentFaq = faqService.getPosts(4);
        model.addAttribute("noticeBoard", noticeBoard);
        model.addAttribute("recentBoard", recentBoard);
        model.addAttribute("recentFaq", recentFaq);
        return "support/support_home";

    }

    @GetMapping("/qna")
    public String  supportQna() {
        return "support/support_qna";
    }


}

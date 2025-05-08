package com.example.gigabox.controller;

import com.example.gigabox.dto.Qna;
import com.example.gigabox.dto.QnaForm;
import com.example.gigabox.service.QnaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/qna")
@Controller
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Qna> qnaList = this.qnaService.getLists();
        for(Qna qna : qnaList){
            qna.setAnswerCheck(qna.getCommentList() != null && !qna.getCommentList().isEmpty());
        }
        model.addAttribute("qnaList", qnaList);
        return "mypage/qna_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        Qna qna = this.qnaService.getOne(id);
        model.addAttribute("qna", qna);
        return "mypage/qna_detail";
    }

    @PostMapping("/create")
    public String create(@Valid QnaForm qnaForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "support/support_qna";
        }

        this.qnaService.create(qnaForm.getQnaType(), qnaForm.getQnaSelect(), qnaForm.getUsername(), qnaForm.getSubject(), qnaForm.getContent(), qnaForm.getFile());
        return "redirect:/qna/list";
    }
    @GetMapping("/create")
    public String create(QnaForm qnaForm, Model model) {
        // qnaType의 기본값을 설정
        if (qnaForm.getQnaType() == null) {
            qnaForm.setQnaType("고객센터문의"); // 기본값을 설정
        }

        model.addAttribute("qnaForm", qnaForm);
        return "support/support_qna"; // Thymeleaf 템플릿 파일
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Qna qna = this.qnaService.getOne(id);
        System.out.println("Deleting QnA with id: " + id);
        this.qnaService.delete(qna);
        return "redirect:/qna/list";
    }
}

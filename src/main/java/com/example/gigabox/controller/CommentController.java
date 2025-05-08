package com.example.gigabox.controller;


import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.Comment;
import com.example.gigabox.dto.CommentForm;
import com.example.gigabox.dto.Qna;
import com.example.gigabox.service.BoardService;
import com.example.gigabox.service.CommentService;
import com.example.gigabox.service.QnaService;
import com.example.gigabox.service.UserService;
import com.example.gigabox.users.GigaUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {
    private final QnaService qnaService;
    private final CommentService commentService;
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model, Principal principal) {
        // 댓글 리스트와 게시글을 가져오는 로직
        Qna qna = qnaService.getOne(id);
        model.addAttribute("qna", qna);
        model.addAttribute("currentUsername", principal.getName()); // 현재 사용자 이름을 추가
        return "mypage/qna_detail";
    }

    //게시글 하나에 대한 댓글이라 id 있어야해
    @GetMapping("/create/{id}")
    public String create(CommentForm commentForm) {
        return "mypage/qna_detail";
    }

    @PostMapping("/create/{id}")
    //model이 있어야 상세보기에도 보내고,
    public String create(@Valid CommentForm commentForm, BindingResult bindingResult, Model model, @PathVariable("id") Long id, Principal principal) {
        //매개변수 id값을 전달받아서 댓글 작성할 게시글 데이터를 가져와서 board에 저장
        Qna qna = this.qnaService.getOne(id);
        GigaUser gigaUser = this.userService.getUsername(principal.getName());
        //게시글 데이터(board_id)와 댓글 내용을 저장하기 위해 commentService의 create()메서드 호출
        //유효성 검사 중 에러 발생하면 그 결과를 저장
        if(bindingResult.hasErrors()){
            model.addAttribute("qna", qna);
            // 에러 메시지 출력
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "mypage/qna_detail";
        }
        this.commentService.create(qna, commentForm.getContent(), gigaUser);
        //댓글이 달린 게시글의 상세보기로 이동
        return String.format("redirect:/qna/detail/%s", id);
    }

    //댓글 1개 가져오기
    @GetMapping("/modify/{id}")
    public String modify(CommentForm commentForm, @PathVariable("id") Long id){
        //매개변수로 전달받은 id값에 해당하는 댓글을 가져와서 comment에 저장
        Comment comment = this.commentService.getOne(id);
        //유효성 검사된 댓글 내용을 셋팅
        commentForm.setContent(comment.getContent());
        //board폴더 안의 comment_form.html 페이지로 이동
        return "mypage/qna_detail";
    }

    //댓글삭제하기
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        //commentService의 getOne메서드를 호툴하면서 id를 매개변수로 전달
        //getOne메서드에 의해 가져온 댓글을 comment인스턴스에 저장
        Comment comment = this.commentService.getOne(id);
        //commentService의 delete메서드 호출하면서 댓글을 매개변수로 전달
        this.commentService.delete(comment);
        //댓글이 지워진 게시글의 상세보기 페이지로 이동
        return String.format("redirect:/qna/detail/%s", comment.getQna().getId());
    }



}

package com.example.gigabox.controller;


import com.example.gigabox.board.BoardForm;
import com.example.gigabox.dto.Board;
import com.example.gigabox.repository.BoardRepository;
import com.example.gigabox.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/support")
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/support_list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "search", required = false) String search){
//        List<Board> boardList = this.boardService.getList();
        Page<Board> paging = boardService.getList(page - 1, search);
        model.addAttribute("paging", paging);
        model.addAttribute("search", search);
        model.addAttribute("boardList", paging.getContent());
        return "support/support_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        Board board = this.boardService.getOne(id);
        model.addAttribute("board", board);
        return "support/support_detail";
    }

    @PostMapping("/create")
    public String create(@Valid BoardForm boardForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "support/support_create";
        }
        this.boardService.create(boardForm.getBoardtype(), boardForm.getTheater(), boardForm.getSubject(), boardForm.getContent());
        return "redirect:/support/support_list";
    }

    @GetMapping("/create")
    public String create(BoardForm boardForm) {
        return "support/support_create";
    }


    //원래 작성되어 있던 내용을 불러가지고 오는 것
    @GetMapping("/modify/{id}")
    public String modify(BoardForm boardForm, @PathVariable("id")Long id){
        Board board = this.boardService.getOne(id);
        boardForm.setBoardtype(board.getBoardtype());
        boardForm.setTheater(board.getTheater());
        boardForm.setSubject(board.getSubject());
        boardForm.setContent(board.getContent());
        return "support/support_create";
    }

    //다시 작성한 걸 등록해야하기 떄문에 post도 만들어야함
    @PostMapping("/modify/{id}")
    public String modify(@Valid BoardForm boardForm, BindingResult bindingResult,@PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "support/support_create";
        }

        Board board = this.boardService.getOne(id);
        this.boardService.modify(board, boardForm.getBoardtype(), boardForm.getTheater(), boardForm.getSubject(), boardForm.getContent());
        return String.format("redirect:/support/detail/%s", id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Board board = this.boardService.getOne(id);
        this.boardService.delete(board);
        return "redirect:/support/support_list";
    }

}

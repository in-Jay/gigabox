package com.example.gigabox.service;

import com.example.gigabox.dto.Board;
import com.example.gigabox.repository.BoardRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;



    public List<Board> getList() {
        return this.boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }



    public Board getOne(Long id){
        Optional<Board> board = this.boardRepository.findById(id);
        return board.get();
    }

    // 줄 바꿈을 <br>로 변환하는 메서드
    private String formatContent(String content) {
        if (content != null) {
            // 줄 바꿈(\n)을 <br>로 변환
            return content.replace("\n", "<br>");
        }
        return content;
    }

    public void create(String boardtype, String theater, String subject, String content){
        // 줄 바꿈을 <br>로 변환
        String formattedContent = formatContent(content);
        Board board = new Board();
        board.setBoardtype(boardtype);
        board.setTheater(theater);
        board.setSubject(subject);
        board.setContent(formattedContent); // 변환된 content 저장
        this.boardRepository.save(board);
    }

    //글수정
    public void modify(Board board, String boardtype, String theater, String subject, String content){
        board.setBoardtype(boardtype);
        board.setTheater(theater);
        board.setSubject(subject);
        board.setContent(content);
        this.boardRepository.save(board);
    }

    //글삭제
    public void delete(Board board){
        this.boardRepository.delete(board);
    }

    //페이징 기능이 있는 게시판 목록 가져오기(검색기능 포함)
    public Page<Board> getList(int page, String search){
        Pageable pageable = PageRequest.of(page, 10);
        if(search != null && !search.isEmpty()){
            //제목 또는 내용으로 검색
            return boardRepository.findBySubjectContainingOrContentContaining(search, search, pageable);
        }else {
            //검색어 없으면 전체 게시물 조회
            return boardRepository.findAll(pageable);
        }
    }

    //최근 게시글 5개 가져오기
    public List<Board> getPosts(int count) {
        return boardRepository.findAllByOrderByIdDesc(PageRequest.of(0, count)).getContent();
    }

}

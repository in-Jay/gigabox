package com.example.gigabox.service;


import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.Qna;
import com.example.gigabox.dto.QnaForm;
import com.example.gigabox.repository.QnaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class QnaService {
    private final QnaRepository qnaRepository;
    //게시글 목록 가져오기
    public List<Qna> getLists(){
        return this.qnaRepository.findAll();
    }

    //게시글 쓰기
    public void create(String qnaType, String qnaSelect, String username, String subject, String content, MultipartFile file){
        Qna qna = new Qna();
        qna.setQnaType(qnaType);
        qna.setQnaSelect(qnaSelect);
        qna.setUsername(username);
        qna.setSubject(subject);
        qna.setContent(content);
        qnaRepository.save(qna);
    }

    //qna테이블에 id에 해당하는 레코드 1개만 가져오기
    public Qna getOne(Long id){
        Optional<Qna> qna = this.qnaRepository.findById(id);
        return qna.get();
    }

    //글삭제
    public void delete(Qna qna){
        this.qnaRepository.delete(qna);

    }

    //최근 게시글 4개 가져오기
    public List<Qna> getPosts() {
        List<Qna> qnas = qnaRepository.findAll(Sort.by(Sort.Order.desc("id")));
        return qnas.stream().limit(4).collect(Collectors.toList());
    }
}

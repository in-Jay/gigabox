package com.example.gigabox.service;

import com.example.gigabox.dto.Board;
import com.example.gigabox.dto.Faq;
import com.example.gigabox.repository.FaqRepository;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqService {
    @Autowired
    private final FaqRepository faqRepository;

    public List<Faq> getByCategory(String category) {
        return faqRepository.findByCategory(category);
    }

    public Page<Faq> getList(int page, String search) {
        Pageable pageable = PageRequest.of(page, 10);
        if(search != null && !search.isEmpty()){
            return faqRepository.findByQuestionContainingOrAnswerContaining(search, search, pageable);
        }else {
            return this.faqRepository.findAll(pageable);
        }

    }


    public Faq getOne(Long id){
        Optional<Faq> faq = this.faqRepository.findById(id);
        return faq.get();
    }


    public Page<Faq> getFaqByCategory(String category, int page) {
        Pageable pageableByCategory = PageRequest.of(page, 10); // 한 페이지당 10개의 FAQ
        return faqRepository.findByCategory(category, pageableByCategory);
    }

    // 줄 바꿈을 <br>로 변환하는 메서드
    private String formatAnswer(String answer) {
        if (answer != null) {
            // 줄 바꿈(\n)을 <br>로 변환
            return answer.replace("\n", "<br>");
        }
        return answer;
    }

    public void create(String category, String question, String answer) {
        String formattedAnswer = formatAnswer(answer);
        Faq f = new Faq();
        f.setCategory(category);
        f.setQuestion(question);
        f.setAnswer(formattedAnswer);
        this.faqRepository.save(f);
    }

    //글삭제
    public void delete(Faq faq){
        this.faqRepository.delete(faq);
    }


    //최근 게시글 5개 가져오기
    public List<Faq> getPosts(int count) {
        return faqRepository.findAllByOrderByIdDesc(PageRequest.of(0, count)).getContent();
    }



    public long getTotalCount() {
        return faqRepository.count();
    }

    public long getTotalCountForRes() {
        return faqRepository.countByCategory("영화예매");
    }

    public long getTotalCountForPay() {
        return faqRepository.countByCategory("결제/관람권");
    }

    public long getTotalCountForTheater() {
        return faqRepository.countByCategory("극장");
    }

    public long getTotalCountForStore() {
        return faqRepository.countByCategory("스토어");
    }

    public long getTotalCountForPage() {
        return faqRepository.countByCategory("홈페이지/모바일");
    }


}

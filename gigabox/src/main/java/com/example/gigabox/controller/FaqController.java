package com.example.gigabox.controller;

import com.example.gigabox.dto.Faq;
import com.example.gigabox.dto.FaqForm;
import com.example.gigabox.repository.FaqRepository;
import com.example.gigabox.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/faq")
@Controller
public class FaqController {
    private final FaqRepository faqRepository;
    private final FaqService faqService;


    @GetMapping("")
    public String  supportFaq(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "search", required = false) String search) {
        Page<Faq> paging = faqService.getList(page, search);
        Page<Faq> resFaq = faqService.getFaqByCategory("영화예매", page);
        Page<Faq> payFaq = faqService.getFaqByCategory("결제/관람권", page);
        Page<Faq> theaterFaq = faqService.getFaqByCategory("극장", page);
        Page<Faq> storeFaq = faqService.getFaqByCategory("스토어", page);
        Page<Faq> pageFaq = faqService.getFaqByCategory("홈페이지/모바일", page);

        model.addAttribute("paging", paging);
        model.addAttribute("resFaq", resFaq);
        model.addAttribute("payFaq", payFaq);
        model.addAttribute("theaterFaq", theaterFaq);
        model.addAttribute("storeFaq", storeFaq);
        model.addAttribute("pageFaq", pageFaq);

        long totalCount = faqService.getTotalCount();
        long resCount = faqService.getTotalCountForRes();
        long payCount = faqService.getTotalCountForPay();
        long theaterCount = faqService.getTotalCountForTheater();
        long storeCount = faqService.getTotalCountForStore();
        long pageCount = faqService.getTotalCountForPage();

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("resCount", resCount);
        model.addAttribute("payCount", payCount);
        model.addAttribute("theaterCount", theaterCount);
        model.addAttribute("storeCount", storeCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("search", search);


        return "support/support_faq";
    }

    //자주묻는질문 글작성
    @PostMapping("/create")
    public String create(@Valid FaqForm faqForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "support/support_faq";
        }
        this.faqService.create(faqForm.getCategory(), faqForm.getQuestion(), faqForm.getAnswer());
        return "redirect:/faq";
    }
    @GetMapping("/create")
    public String create(Model model) {
        // qnaType의 기본값을 설
        return "support/support_faq"; // Thymeleaf 템플릿 파일
    }

    //자주묻는 질문 글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Faq faq = this.faqService.getOne(id);
        this.faqService.delete(faq);
        return "redirect:/faq";
    }


}

package com.example.gigabox.controller;



import com.example.gigabox.dto.Theater;
import com.example.gigabox.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/theater")
@Controller
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/theater1")
    public String theater1(Model model) {
        List<Theater> theater1 = theaterService.getTheaterByArea("서울");
        model.addAttribute("theater1", theater1);
        return "theater/list";
    }

    @GetMapping("/theater2")
    public String theater2(Model model) {
        List<Theater> theater2 = theaterService.getTheaterByArea("경기");
        model.addAttribute("theater2", theater2);
        return "theater/list";
    }

    @GetMapping("/theater3")
    public String theater3(Model model) {
        List<Theater> theater3 = theaterService.getTheaterByArea("인천");
        model.addAttribute("theater3", theater3);
        return "theater/list";
    }

    @GetMapping("/theater4")
    public String theater4(Model model) {
        List<Theater> theater4 = theaterService.getTheaterByArea("대전/충청/세종");
        model.addAttribute("theater4", theater4);
        return "theater/list";
    }

    @GetMapping("/theater5")
    public String theater5(Model model) {
        List<Theater> theater5 = theaterService.getTheaterByArea("부산/대구/경상");
        model.addAttribute("theater5", theater5);
        return "theater/list";
    }

    @GetMapping("/theater6")
    public String theater6(Model model) {
        List<Theater> theater6 = theaterService.getTheaterByArea("광주/전라");
        model.addAttribute("theater6", theater6);
        return "theater/list";
    }

    @GetMapping("/theater7")
    public String theater7(Model model) {
        List<Theater> theater7 = theaterService.getTheaterByArea("강원");
        model.addAttribute("theater7", theater7);
        return "theater/list";
    }

    @GetMapping("/theater8")
    public String theater8(Model model) {
        List<Theater> theater8 = theaterService.getTheaterByArea("제주");
        model.addAttribute("theater8", theater8);
        return "theater/list";
    }

    @GetMapping("/detail/{id}")
    public String theaterDetail(Model model, @PathVariable("id") Long id){
        Theater theater = this.theaterService.getOneById(id);
        if(theater == null) {
            throw new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다.");
        }
        System.out.println("조회할 ID: " + id);
        model.addAttribute("theater", theater);
        return "theater/detail";

    }

}


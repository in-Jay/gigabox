package com.example.gigabox.controller;

import com.example.gigabox.dto.Item;
import com.example.gigabox.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/store")
@Controller
public class StoreController {
    private final ApiService apiService;

    public StoreController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("")
    public String store(Model model){
        List<Item> storeItem1 = apiService.searchItem("메가박스부티크");
        List<Item> storeItem2 = apiService.searchItem("메가박스팝콘");
        List<Item> storeItem3 = apiService.searchItem("메가박스굿즈");
        model.addAttribute("storeItem1", storeItem1);
        model.addAttribute("storeItem2", storeItem2);
        model.addAttribute("storeItem3", storeItem3);
        return "store";
    }

    @GetMapping("/detail/{id}")
    public String storeDetail(Model model, @PathVariable("id") Long id){
        Item detail = this.apiService.getOne(id);
        if(detail == null) {
            throw new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다.");
        }
        System.out.println("조회할 ID: " + id);
        model.addAttribute("detail", detail);
        return "store/detail";

    }
    @GetMapping("/recom")
    public String recom() {
        return "store/recom";
    }
}

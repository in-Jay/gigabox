package com.example.gigabox.controller;


import com.example.gigabox.dto.Item;
import com.example.gigabox.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
@ResponseBody
@RequiredArgsConstructor
@Controller
public class ApiController {
    private final ApiService apiService;
    @GetMapping("/api")
    public List<Item> getItems(@RequestParam("query") String query){
        query = URLDecoder.decode(query, StandardCharsets.UTF_8);
        return apiService.searchItem(query);
    }
}

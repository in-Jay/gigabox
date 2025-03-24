package com.example.gigabox.dto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverSearch {
    public String search (String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "EXHNOzuGJdJHVijeXak2");
        headers.add("X-Naver-Client-Secret", "FIhzT_DvOn");
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + query;
        ResponseEntity<String> responseEntity = rest.exchange(apiURL, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }
}

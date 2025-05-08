package com.example.gigabox.service;


import com.example.gigabox.dto.Item;
import com.example.gigabox.dto.NaverSearch;
import com.example.gigabox.repository.ItemApiRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final NaverSearch naverSearch;
    private final ItemApiRepository itemApiRepository;

    public List<Item> searchItem(String query){
        //naverSearch클래스의 search메서드 호출하면서 검색어(예:라면)를 매개변수로 전달
        String result = naverSearch.search(query);
        //검색어로 검색한 결과를 fromJSONtoItems메서드로 전달
        return fromJSONtoItems(result);
    }
    //JSON 형식의 응답 문자열을 Item 클래스 형식의 배열로 변환하는 메서드
    public List<Item> fromJSONtoItems(String result) {
        JSONObject json = new JSONObject(result);
        JSONArray items = json.getJSONArray("items");

        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            String title = Jsoup.parse(itemJson.getString("title")).text();

            Item item = new Item();
            item.setTitle(title);
            item.setLink(itemJson.getString("link"));
            item.setImage(itemJson.getString("image"));
            item.setLprice(itemJson.getInt("lprice"));
            item.setProductId(itemJson.getString("productId"));
            item.setCategory3(itemJson.getString("category3"));
            // 데이터베이스에 저장
            Item savedItem = itemApiRepository.save(item);
            itemList.add(savedItem);
        }
        return itemList;
    }

    //모든 상품 목록 가져오기
    public List<Item> getAllItem(){
        return itemApiRepository.findAll();
    }

    public Item getOne(Long id) {
        return itemApiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + id));
    }

}
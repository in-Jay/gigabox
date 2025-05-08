package com.example.gigabox.service;

import com.example.gigabox.dto.Theater;
import com.example.gigabox.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;


    //극장 전부 가져오기
    public List<Theater> getAll(){
       return theaterRepository.findAll();
    }

    //극장 정보 1개만 가져오기
    public Theater getOneById(Long id){
        return theaterRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
    }

    //지역별로 극장 가져오기
    public List<Theater> getTheaterByArea(String area){
        return theaterRepository.findByArea(area);
    }
}

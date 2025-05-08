package com.example.gigabox.service;

import com.example.gigabox.dto.Restheater;
import com.example.gigabox.dto.Theater;
import com.example.gigabox.repository.RestheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RestheaterService {
    private final RestheaterRepository restheaterRepository;
    //극장 전부 가져오기
    public List<Restheater> getAll(){
        return restheaterRepository.findAll();
    }

    //극장 정보 1개만 가져오기
    public Restheater getOneById(Long id){
        return restheaterRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
    }

    //지역별로 극장 가져오기
    public List<Restheater> getRestheaterByArea(String area){
        return restheaterRepository.findByArea(area);
    }
}

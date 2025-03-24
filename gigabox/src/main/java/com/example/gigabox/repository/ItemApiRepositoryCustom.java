package com.example.gigabox.repository;

import com.example.gigabox.dto.Item;

import java.util.List;

public interface ItemApiRepositoryCustom {
    List<Item> findByCategory(String category);
}
